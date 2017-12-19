//package com.meilun.security.smart.cateye.view;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.Toolbar;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.util.Base64;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.Player.web.response.DevItemInfo;
//import com.aglhz.cateye.App;
//import com.aglhz.cateye.R;
//import com.aglhz.cateye.common.Constants;
//import com.aglhz.cateye.common.DialogHelper;
//import com.aglhz.cateye.entity.DevicePreferencesBean;
//import com.alibaba.fastjson.JSON;
//import com.google.gson.Gson;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import cn.itsite.abase.common.RxManager;
//import cn.itsite.abase.log.ALog;
//import cn.itsite.abase.mvp.view.base.BaseFragment;
//import cn.itsite.abase.utils.KeyBoardUtils;
//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Author: LiuJia on 2017/5/4 0004 19:21.
// * Email: liujia95me@126.com
// */
//
//public class UpdatePasswordFragment extends BaseFragment implements TextWatcher {
//    public static final String TAG = UpdatePasswordFragment.class.getSimpleName();
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.tv_username_update_password_fragment)
//    TextView tvUsername;
//    @BindView(R.id.et_old_password_update_password_fragment)
//    EditText etOldPassword;
//    @BindView(R.id.et_new_password_update_password_fragment)
//    EditText etNewPassword;
//    @BindView(R.id.et_repeat_update_password_fragment)
//    EditText etRepeatUpdate;
//    @BindView(R.id.bt_save_update_password_fragment)
//    Button btSave;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    private DevItemInfo device;
//    private String[] connectParams;
//    private String devUserName;
//    private String devUserPwd;
//    private String devId;
//    private Unbinder unbinder;
//    private RxManager mRxManager = new RxManager();
//    private String strPassword;
//
//    public static UpdatePasswordFragment newInstance(DevItemInfo device) {
//        Bundle args = new Bundle();
//        args.putSerializable(Constants.DEVICE, device);
//        UpdatePasswordFragment fragment = new UpdatePasswordFragment();
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
//        View view = inflater.inflate(R.layout.fragment_update_password, container, false);
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
//        toolbarTitle.setText("修改密码");
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
//        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
//    }
//
//    private void initData() {
//        parseParams();
//        tvUsername.setText(device.node_name);
//        etOldPassword.addTextChangedListener(this);
//        etNewPassword.addTextChangedListener(this);
//        etRepeatUpdate.addTextChangedListener(this);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        KeyBoardUtils.hideKeybord(etNewPassword, _mActivity);
//        unbinder.unbind();
//    }
//
//    @OnClick(R.id.bt_save_update_password_fragment)
//    public void onViewClicked() {
//
//        if (!devUserPwd.equals(etOldPassword.getText().toString())) {
//            DialogHelper.warningSnackbar(getView(), "原始密码错误！");
//            return;
//        }
//
//        if (!etNewPassword.getText().toString().equals(etRepeatUpdate.getText().toString())) {
//            DialogHelper.warningSnackbar(getView(), "两次密码输入不一致！");
//            return;
//        }
//        setDeviceInfo();
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        boolean enabled = TextUtils.isEmpty(etOldPassword.getText().toString())
//                || TextUtils.isEmpty(etNewPassword.getText().toString())
//                || TextUtils.isEmpty(etRepeatUpdate.getText().toString());
//        btSave.setEnabled(!enabled);
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//
//    }
//
//    private void parseParams() {
//        if (device == null) {
//            DialogHelper.errorSnackbar(getView(), "找不到设备信息！");
//            return;
//        }
//
//        if (connectParams != null) {
//            return;
//        }
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
//
//    private void setDeviceInfo() {
//        strPassword = etNewPassword.getText().toString();
//        mRxManager.add(Observable.create((Observable.OnSubscribe<String>)
//                subscriber -> {
//                    subscriber.onStart();
//                    byte[] request = "{Operation:1,Request_Type:0}".getBytes();
//                    byte[] result = App.mPlayerClient.CallCustomFunc(devId, devUserName, devUserPwd, 66052, request);
//
//                    DevicePreferencesBean response = JSON.parseObject(new String(result), DevicePreferencesBean.class);
//                    strPassword = new String(Base64.encode(strPassword.getBytes(), Base64.DEFAULT));
//                    response.getValue().setDev_Psw(strPassword);
//                    response.setOperation(2);
//                    response.setRequest_Type(1);
//                    byte[] response1 = App.mPlayerClient.CallCustomFunc(devId, devUserName, devUserPwd, 66052, new Gson().toJson(response).getBytes());
//                    subscriber.onNext(new String(response1));
//                    subscriber.onCompleted();
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        showLoading();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        throwable.printStackTrace();
//                        dismissLoading();
//                        DialogHelper.errorSnackbar(getView(), "设置失败，请重试！");
//                    }
//
//                    @Override
//                    public void onNext(String response) {
//                        ALog.e("response-->" + response);
//                        if (JSON.parseObject(response).get("Result").equals(1)) {
//                            DialogHelper.successSnackbar(getView(), "设置成功！");
//                        } else {
//                            DialogHelper.errorSnackbar(getView(), "设置失败！");
//                        }
//                    }
//                }));
//    }
//}
