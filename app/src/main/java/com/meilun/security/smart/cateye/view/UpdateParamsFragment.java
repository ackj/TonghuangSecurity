//package com.meilun.security.smart.cateye.view;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.Toolbar;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.Player.web.response.DevItemInfo;
//import com.Player.web.response.ResponseCommon;
//import com.Player.web.websocket.ClientCore;
//import com.meilun.security.smart.R;
//import com.meilun.security.smart.common.Constants;
//import com.meilun.security.smart.common.Params;
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
// * Author: LiuJia on 2017/5/4 0004 19:21.
// * Email: liujia95me@126.com
// */
//
//public class UpdateParamsFragment extends BaseFragment implements TextWatcher {
//    public static final String TAG = UpdateParamsFragment.class.getSimpleName();
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    Unbinder unbinder;
//    @BindView(R.id.et_device_name_update_params_fragment)
//    EditText etDeviceName;
//    @BindView(R.id.et_serial_number_update_params_fragment)
//    EditText etSerialNumber;
//    @BindView(R.id.et_password_update_params_fragment)
//    EditText etPassword;
//    @BindView(R.id.bt_save_update_params_fragment)
//    Button btSave;
//    private DevItemInfo device;
//    private String[] connectParams;
//    private String devUserName;
//    private String devUserPwd;
//    private String devId;
//    private Params params = Params.getInstance();
//
//    public static UpdateParamsFragment newInstance(DevItemInfo device) {
//        Bundle args = new Bundle();
//        args.putSerializable(Constants.DEVICE, device);
//        UpdateParamsFragment fragment = new UpdateParamsFragment();
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
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_update_params, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        return attachToSwipeBack(view);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initToolbar();
//        initData();
//    }
//
//    private void initToolbar() {
//        initStateBar(toolbar);
//        toolbarTitle.setText("修改连接参数");
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
//        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
//    }
//
//    private void initData() {
//        parseParams();
//        etDeviceName.setText(device.node_name);
//        etSerialNumber.setText(devId);
//        etPassword.setText(devUserPwd);
//        etDeviceName.addTextChangedListener(this);
//        etSerialNumber.addTextChangedListener(this);
//        etPassword.addTextChangedListener(this);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    @OnClick(R.id.bt_save_update_params_fragment)
//    public void onViewClicked() {
//        params.deviceName = etDeviceName.getText().toString();
//        String serialNumber = etSerialNumber.getText().toString();
//        String password = etPassword.getText().toString();
//        showLoading();
//        ClientCore.getInstance().modifyNodeInfo(device.node_id + "", params.deviceName,
//                device.node_type, device.id_type, device.vendor_id, serialNumber, "",
//                0, devUserName, password, device.dev_ch_no, device.dev_stream_no, new Handler() {
//
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        dismissLoading();
//                        ResponseCommon response = (ResponseCommon) msg.obj;
//                        if ((response != null) && (response.h != null) && (response.h.e == 200)) {
//                            ALog.e(response.h.e);
//                            DialogHelper.successSnackbar(getView(), "恭喜，修改成功!");
//                            EventBus.getDefault().post(new EventRefreshDeviceList());
//                            pop();
//                        } else {
//                            DialogHelper.errorSnackbar(getView(), "抱歉，修改失败!");
//                        }
//                    }
//                });
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        boolean enabled = TextUtils.isEmpty(etDeviceName.getText().toString())
//                || TextUtils.isEmpty(etSerialNumber.getText().toString())
//                || TextUtils.isEmpty(etPassword.getText().toString());
//        btSave.setEnabled(!enabled);
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//
//    }
//
//    //解析设备参数，如果已经解析过，再次点击播放的时候就不需要解析。
//    private void parseParams() {
//        if (device == null) {
//            DialogHelper.errorSnackbar(getView(), "找不到设备信息！");
//            return;
//        }
//
//        if (connectParams != null) {
//            return;
//        }
//        ALog.e("device.conn_params-->" + device.conn_params);
//        //解析参数
//        connectParams = device.conn_params.split(",");
//        devUserName = "";
//        devId = "";
//        devUserPwd = "";
//
//        for (int i = 0; i < connectParams.length; i++) {
//            if (connectParams[i].contains("UserName")) {
//                devUserName = connectParams[i].split("=")[1];
//                continue;
//            }
//            if (connectParams[i].contains("UserPwd")) {
//                devUserPwd = connectParams[i].split("=")[1];
//                continue;
//            }
//            if (connectParams[i].contains("DevId")) {
//                devId = connectParams[i].split("=")[1];
//            }
//        }
//        ALog.e(devUserName);
//        ALog.e(devId);
//        ALog.e(devUserPwd);
//    }
//}
