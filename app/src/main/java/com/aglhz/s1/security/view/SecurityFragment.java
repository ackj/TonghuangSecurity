package com.aglhz.s1.security.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.bean.GatewaysBean;
import com.aglhz.s1.bean.SecurityBean;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.data.SecurityData;
import com.aglhz.s1.security.contract.SecurityContract;
import com.aglhz.s1.security.presenter.SecurityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.DensityUtils;
import cn.itsite.multiselector.MultiSelectorDialog;


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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    private SecurityRVAdapter adapter;
    private Params params = Params.getInstance();
    private MultiSelectorDialog switchGatewayDialog;
    private GatewaysBean gateways;
    private TextView tvHome;
    private TextView tvCancel;
    private TextView tvFaraway;
    private TextView tvMessage;

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
        View view = inflater.inflate(R.layout.fragment_security, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("GS-S1智能安防");
        toolbarMenu.setText("切换主机");
        toolbarMenu.setOnClickListener(v -> {
            mPresenter.requestGateways(params);
            showLoading();
        });
    }

    private void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        adapter = new SecurityRVAdapter(SecurityData.getInstance().getAlreadyAddSecuritys());
        adapter.setHeaderView(initHeaderView());
        recyclerView.setAdapter(adapter);

        switchGatewayDialog = MultiSelectorDialog.builder(_mActivity)
                .setTitle("请选择您要切换的主机")
                .setTabVisible(false)
                .setOnItemClickListener((pagerPosition, optionPosition, option) -> {
                    switchGatewayDialog.dismiss();
                    ALog.e("pagerPosition-->" + pagerPosition + "\r\noptionPosition-->" + optionPosition + "\r\noption-->" + option);
                    params.gateway = gateways.getData().get(optionPosition).getFid();
                    mPresenter.requestSwichGateway(params);
                })
                .build();
    }

    private View initHeaderView() {
        View headerView = LayoutInflater.from(_mActivity).inflate(R.layout.item_security_header, null);
        TextView tvDescHeader = (TextView) headerView.findViewById(R.id.tv_desc_security_header);
        String title = "离家布防：";
        String desc = "当主机处于离家布防状态时，所有开启的探测器都处于防御状态。";
        tvDescHeader.setText(title + desc);
        Spannable span = new SpannableString(tvDescHeader.getText());
        span.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(_mActivity, 18)), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDescHeader.setText(span);

        //初始化撤防、布防等View。
        tvCancel = (TextView) headerView.findViewById(R.id.tv_cancel_item_security_header);
        tvHome = (TextView) headerView.findViewById(R.id.tv_home_item_security_header);
        tvFaraway = (TextView) headerView.findViewById(R.id.tv_faraway_item_security_header);
        tvMessage = (TextView) headerView.findViewById(R.id.tv_message_item_security_header);
        //设置状态。
        tvCancel.setOnClickListener(v -> {
            tvCancel.setPressed(true);
            tvHome.setPressed(false);
            tvFaraway.setPressed(false);
        });

        tvHome.setOnClickListener(v -> {
            tvHome.setPressed(true);
            tvCancel.setPressed(false);
            tvFaraway.setPressed(false);
        });

        tvFaraway.setOnClickListener(v -> {
            tvFaraway.setPressed(true);
            tvCancel.setPressed(false);
            tvFaraway.setPressed(false);
        });
        return headerView;
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            ALog.e("onItemclick position:" + position + " size:" + adapter.getData().size());
            if (position == adapter.getData().size() - 1) {
                _mActivity.start(AddDetectorFragment.newInstance());
            } else {
                _mActivity.start(DetectorPropertyFragment.newInstance());
            }
            Toast.makeText(_mActivity, "clickItem", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        dismissLoading();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseSecurity(List<SecurityBean> listRecord) {
        //todo:
    }

    @Override
    public void responseGateways(GatewaysBean gateways) {
        this.gateways = gateways;
        dismissLoading();
        switchGatewayDialog.show();
        if (gateways != null && gateways.getData() != null) {
            List<String> list = new ArrayList<>();
            for (GatewaysBean.DataBean dataBean : gateways.getData()) {
                ALog.e("dataBean.getName()--》" + dataBean.getName());
                list.add(dataBean.getName());
            }
            getView().post(() -> switchGatewayDialog.notifyDataSetChanged(list));
        } else {
            DialogHelper.errorSnackbar(getView(), "您尚未配置网关！");
        }
    }

    @Override
    public void responseSwichGateway(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), baseBean.getOther().getMessage());
    }

}
