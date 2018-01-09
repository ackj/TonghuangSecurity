//package com.meilun.security.smart.cateye.view;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.Player.web.response.DevItemInfo;
//import com.Player.web.response.ResponseCommon;
//import com.Player.web.websocket.ClientCore;
//import com.meilun.security.smart.R;
//import com.meilun.security.smart.cateye.contract.DeviceSettingContract;
//import com.meilun.security.smart.cateye.presenter.DeviceSettingPresenter;
//import com.meilun.security.smart.common.Constants;
//import com.meilun.security.smart.common.Params;
//import com.meilun.security.smart.common.UserHelper;
//import com.meilun.security.smart.entity.bean.BaseBean;
//
//import org.greenrobot.eventbus.EventBus;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import cn.itsite.abase.common.DialogHelper;
//import cn.itsite.abase.log.ALog;
//import cn.itsite.abase.mvp.view.base.BaseFragment;
//
///**
// * Author: LiuJia on 2017/5/4 0004 16:05.
// * Email: liujia95me@126.com
// */
//
//public class DeviceSettingsFragment extends BaseFragment<DeviceSettingContract.Presenter> implements DeviceSettingContract.View {
//    public static final String TAG = UpdatePreferencesFragment.class.getSimpleName();
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    Unbinder unbinder;
//    private DevItemInfo device;
//    private Params params = Params.getInstance();
//
//    public static DeviceSettingsFragment newInstance(DevItemInfo device) {
//        Bundle args = new Bundle();
//        args.putSerializable(Constants.DEVICE, device);
//        DeviceSettingsFragment fragment = new DeviceSettingsFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle args = getArguments();
//        if (args != null) {
//            device = (DevItemInfo) args.getSerializable(Constants.DEVICE);
//        }
//    }
//
//    @NonNull
//    @Override
//    protected DeviceSettingContract.Presenter createPresenter() {
//        return new DeviceSettingPresenter(this);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_device_settings, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        return attachToSwipeBack(view);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initToolbar();
//    }
//
//    private void initToolbar() {
//        initStateBar(toolbar);
//        toolbarTitle.setText("设置");
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
//        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    @OnClick({R.id.ll_change_params_fragment_device_settings, R.id.ll_device_settings_fragment_device_settings, R.id.tv_delete_fragment_device_settings})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.ll_change_params_fragment_device_settings:
//                start(UpdateParamsFragment.newInstance(device));
//                break;
//            case R.id.ll_device_settings_fragment_device_settings:
//                start(UpdatePreferencesFragment.newInstance(device));
//                break;
//            case R.id.tv_delete_fragment_device_settings:
//                showDeleteDialog();
//                break;
//        }
//    }
//
//    private void showDeleteDialog() {
//        new AlertDialog.Builder(_mActivity)
//                .setTitle("警告")
//                .setMessage("确定要删除吗？")
//                .setPositiveButton("删除", (dialog, which) -> deleteDevice())
//                .setNegativeButton("取消", null)
//                .show();
//    }
//
//    private void deleteDevice() {
//        showLoading();
//        params.serialNumber = device.umid;
//        params.account = UserHelper.account;
//        mPresenter.requestDelDevice(params);
//    }
//
//    @Override
//    public void responseDelSuccess(BaseBean baseBean) {
//        ALog.e(TAG,"删除成功");
//        ClientCore.getInstance().deleteNodeInfo(String.valueOf(device.node_id), device.node_type, device.id_type, new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                dismissLoading();
//                ResponseCommon response = (ResponseCommon) msg.obj;
//                if ((response != null) && (response.h != null) && (response.h.e == 200)) {
//                    ALog.e("response.h.e-->" + response.h.e);
//                    DialogHelper.successSnackbar(getView(), "删除成功！");
//                    EventBus.getDefault().post(new EventRefreshDeviceList());
//                    pop();
//                } else {
//                    DialogHelper.errorSnackbar(getView(), "删除失败！");
//                }
//            }
//        });
//    }
//}
