package com.aglhz.s1.security.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DevicesBean;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.entity.bean.NotificationBean;
import com.aglhz.s1.entity.bean.SecurityBean;
import com.aglhz.s1.event.EventRefreshSecurity;
import com.aglhz.s1.security.contract.SecurityContract;
import com.aglhz.s1.security.presenter.SecurityPresenter;
import com.aglhz.s1.widget.PtrHTFrameLayout;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
import cn.itsite.basedialogfragment.BaseDialogFragment;
import cn.itsite.statemanager.StateLayout;


/**
 * Author: LiuJia on 2017/4/26 0026 11:11.
 * Email: liujia95me@126.com
 */

public class SecurityFragment extends BaseFragment<SecurityContract.Presenter> implements SecurityContract.View {
    public static final String TAG = SecurityFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.stateLayout)
    StateLayout stateLayout;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;
    private SecurityRVAdapter adapter;
    private Params params = Params.getInstance();
    private TextView tvHome;
    private TextView tvCancel;
    private TextView tvFaraway;
    private TextView tvMessage;
    private TextView tvDes;
    private DevicesBean.DataBean.DeviceTypeListBean addIconDevice;
    private List<SecurityBean.DataBean.SubDevicesBean> subDevices;
    private BaseRecyclerViewAdapter<GatewaysBean.DataBean, BaseViewHolder> selectorAdapter;

    public static SecurityFragment newInstance() {
        return new SecurityFragment();
    }

    @NonNull
    @Override
    protected SecurityContract.Presenter createPresenter() {
        return new SecurityPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("GS-S1智能安防");
        toolbarMenu.setText("切换主机");
        toolbarMenu.setOnClickListener(v -> mPresenter.requestGateways(params));
    }

    private void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        addIconDevice = new DevicesBean.DataBean.DeviceTypeListBean();
        addIconDevice.setIcon("add_icon");
        addIconDevice.setName("添加探测器");
        adapter = new SecurityRVAdapter();
        adapter.setHeaderView(initHeaderView());
        recyclerView.setAdapter(adapter);
        List<DevicesBean.DataBean.DeviceTypeListBean> data = new ArrayList<>();
        data.add(addIconDevice);
        adapter.setNewData(data);
//        switchGatewayDialog = MultiSelectorDialog.builder(_mActivity)
//                .setTitle("请选择您要切换的主机")
//                .setTabVisible(false)
//                .setOnItemClickListener((pagerPosition, optionPosition, option) -> {
//                    switchGatewayDialog.dismiss();
//                    ALog.e("pagerPosition-->" + pagerPosition + "\r\noptionPosition-->" + optionPosition + "\r\noption-->" + option);
//                    params.gateway = gateways.getData().get(optionPosition).getFid();
//                    mPresenter.requestSwichGateway(params);
//                })
//                .build();
        selectorAdapter = new BaseRecyclerViewAdapter<GatewaysBean.DataBean,
                BaseViewHolder>(R.layout.item_rv_host_selector) {
            @Override
            protected void convert(BaseViewHolder helper, GatewaysBean.DataBean item) {
                helper.setText(R.id.tv_name_item_rv_host_selector, item.getName())
                        .setText(R.id.tv_role_item_rv_host_selector, item.getIsManager() == 1 ? "管理员" : "成员")
                        .setText(R.id.tv_current_item_rv_host_selector, item.getIsCurrent() == 1 ? "当前主机" : "")
                        .setText(R.id.tv_name_item_rv_host_selector, item.getName() + (item.getIsOnline() == 1 ? "" : "　(离线)"));
            }
        };
    }

    @Override
    public void onRefresh() {
        mPresenter.requestSecurity(params);
    }

    private View initHeaderView() {
        View headerView = LayoutInflater.from(_mActivity).inflate(R.layout.item_security_header, null);
        //初始化撤防、布防等View。
        tvDes = (TextView) headerView.findViewById(R.id.tv_des_security_header);

        tvCancel = (TextView) headerView.findViewById(R.id.tv_cancel_item_security_header);
        tvHome = (TextView) headerView.findViewById(R.id.tv_home_item_security_header);
        tvFaraway = (TextView) headerView.findViewById(R.id.tv_faraway_item_security_header);
        tvMessage = (TextView) headerView.findViewById(R.id.tv_message_item_security_header);
        //设置状态。
        tvCancel.setOnClickListener(v -> {
            params.dstatus = Constants.GATEWAY_STATE_CANCLE;
            mPresenter.requestSwichState(params);
        });

        tvHome.setOnClickListener(v -> {
            params.dstatus = Constants.GATEWAY_STATE_HOME;
            mPresenter.requestSwichState(params);
        });

        tvFaraway.setOnClickListener(v -> {
            params.dstatus = Constants.GATEWAY_STATE_FARAWAY;
            mPresenter.requestSwichState(params);
        });
        return headerView;
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            ALog.e("onItemclick position:" + position + " size:" + adapter.getData().size());

            if (position == adapter.getData().size() - 1) {
                _mActivity.start(AddDetectorFragment.newInstance());
            } else {
                if (subDevices == null || subDevices.size() == 0) {
                    return;
                }
                SecurityBean.DataBean.SubDevicesBean bean = subDevices.get(position);
                _mActivity.start(DetectorPropertyFragment.newInstance(bean));
            }
            Toast.makeText(_mActivity, "clickItem", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Override
    public void error(String errorMessage) {
        super.error(errorMessage);
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void responseSecurity(SecurityBean securityBean) {
        ALog.e("securityBean-->" + securityBean.getData().getGateway().getDefenseStatus());
        subDevices = securityBean.getData().getSubDevices();

        TextView tv = (TextView) adapter.getHeaderLayout()
                .findViewWithTag(securityBean.getData().getGateway().getDefenseStatus());
        tvCancel.setSelected(false);
        tvHome.setSelected(false);
        tvFaraway.setSelected(false);
        tv.setSelected(true);

        tvDes.setText(securityBean.getData().getGateway().getDefenseStatusDes());

        List<DevicesBean.DataBean.DeviceTypeListBean> data = new ArrayList<>();

        for (int i = 0; i < subDevices.size(); i++) {
            DevicesBean.DataBean.DeviceTypeListBean bean = new DevicesBean.DataBean.DeviceTypeListBean();
            bean.setName(subDevices.get(i).getName());
            bean.setIcon(subDevices.get(i).getIcon());
            data.add(bean);
        }
        data.add(addIconDevice);
        adapter.setNewData(data);
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void responseGateways(GatewaysBean gateways) {
//        this.gateways = gateways;
//        switchGatewayDialog.show();
//        if (gateways != null && gateways.getData() != null) {
//            List<String> list = new ArrayList<>();
//            for (GatewaysBean.DataBean dataBean : gateways.getData()) {
//                ALog.e("dataBean.getName()--》" + dataBean.getName());
//                list.add(dataBean.getName());
//            }
//            getView().post(() -> switchGatewayDialog.notifyDataSetChanged(list));
//        } else {
//            DialogHelper.errorSnackbar(getView(), "您尚未配置网关！");
//        }

        //-----------------------------------------

        if (gateways == null || gateways.getData() == null
                || gateways.getData().isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "您尚未配置网关！");
            return;
        }
        new BaseDialogFragment()
                .setLayoutId(R.layout.fragment_selector_dialog)
                .setConvertListener((holder, dialog) -> {
                    holder.setText(R.id.tv_title_selector_dialog_fragment, "请选择切换的主机")
                            .setOnClickListener(R.id.iv_cancel_selector_dialog_fragment, v -> dialog.dismiss())
                            .setOnClickListener(R.id.tv_cancel_selector_dialog_fragment, v -> dialog.dismiss());

                    RecyclerView recyclerView = holder.getView(R.id.recyclerView_selector_dialog_fragment);
                    recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                    recyclerView.setAdapter(selectorAdapter);

                    selectorAdapter.setOnItemClickListener((adapter1, view, position) -> {
                        dialog.dismiss();
                        params.gateway = selectorAdapter.getData().get(position).getFid();
                        mPresenter.requestSwichGateway(params);
                    });

                    selectorAdapter.setNewData(gateways.getData());

                })
                .setDimAmount(0.3f)
                .setGravity(Gravity.BOTTOM)
                .setAnimStyle(R.style.SlideAnimation)
                .show(getChildFragmentManager());
    }

    @Override
    public void responseSwichGateway(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), baseBean.getOther().getMessage());
        ptrFrameLayout.autoRefresh();
    }

    @Override
    public void responseSwichState(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), baseBean.getOther().getMessage());
        switch (params.dstatus) {
            case Constants.GATEWAY_STATE_CANCLE:
                tvCancel.setSelected(true);
                tvHome.setSelected(false);
                tvFaraway.setSelected(false);
                break;
            case Constants.GATEWAY_STATE_HOME:
                tvHome.setSelected(true);
                tvCancel.setSelected(false);
                tvFaraway.setSelected(false);
                break;
            case Constants.GATEWAY_STATE_FARAWAY:
                tvFaraway.setSelected(true);
                tvHome.setSelected(false);
                tvCancel.setSelected(false);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshSecurity(EventRefreshSecurity event) {
        ALog.e("刷新安防…………………………………………………………………………");
        if (event.notificationBean == null) {
            return;
        }

        switch (event.notificationBean.getExtra().getType()) {
            case Constants.SENSOR_LEARN:
            case Constants.GW_NOTIFIY_DEFENSE_ST:
                ptrFrameLayout.autoRefresh();
                NotificationBean.ExtraBean extraBean = event.notificationBean.getExtra();
                if (extraBean.getStatus() == 1) {
                    DialogHelper.successSnackbar(getView(), extraBean.getDes());
                } else {
                    DialogHelper.errorSnackbar(getView(), extraBean.getDes());
                }
                break;
        }
    }
}
