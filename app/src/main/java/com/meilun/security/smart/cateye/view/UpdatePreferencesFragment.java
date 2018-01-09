//package com.meilun.security.smart.cateye.view;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.util.Base64;
//import android.util.Log;
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
//import com.kyleduo.switchbutton.SwitchButton;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import cn.itsite.abase.common.RxManager;
//import cn.itsite.abase.log.ALog;
//import cn.itsite.abase.mvp.view.base.BaseFragment;
//import rx.Observable;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Author: LiuJia on 2017/5/4 0004 21:32.
// * Email: liujia95me@126.com
// */
//
//public class UpdatePreferencesFragment extends BaseFragment {
//    public static final String TAG = UpdatePreferencesFragment.class.getSimpleName();
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    Unbinder unbinder;
//    @BindView(R.id.et_doorbell_password_update_preferences_fragment)
//    EditText etDoorbellPassword;
//    @BindView(R.id.et_unlock_password_update_preferences_fragment)
//    EditText etUnlockPassword;
//    @BindView(R.id.sb_audio_update_preferences_fragment)
//    SwitchButton sbAudio;
//    @BindView(R.id.bt_save_update_preferences_fragment)
//    Button btSave;
//    private DevItemInfo device;
//    private String[] params;
//    private String devUserName;
//    private String devUserPwd;
//    private String devId;
//    private RxManager mRxManager = new RxManager();
//    private String strDoorbell;
//    private String strUnlock;
//    private String doorPassword;
//    private String lockPassword;
//
//    public static UpdatePreferencesFragment newInstance(DevItemInfo device) {
//        Bundle args = new Bundle();
//        args.putSerializable(Constants.DEVICE, device);
//        UpdatePreferencesFragment fragment = new UpdatePreferencesFragment();
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
//        View view = inflater.inflate(R.layout.fragment_update_preferences, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        return attachToSwipeBack(view);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initToolbar();
//        iniData();
//    }
//
//    private void initToolbar() {
//        initStateBar(toolbar);
//        toolbarTitle.setText("设备设置");
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
//        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
//    }
//
//    private void iniData() {
//        parseParams();
//        getDeviceInfo();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//        mRxManager.clear();
//    }
//
//    @OnClick(R.id.bt_save_update_preferences_fragment)
//    public void onViewClicked() {
//        if (!etDoorbellPassword.getText().toString().equals(lockPassword)) {
//            DialogHelper.errorSnackbar(getView(), "原始密码不正确！");
////            return;
//        }
//        setDeviceInfo();
//    }
//
//    void getDeviceInfo() {
//        mRxManager.add(Observable.create((Observable.OnSubscribe<DevicePreferencesBean>)
//                        subscriber -> {
//                            byte[] request = "{Operation:1,Request_Type:0}".getBytes();
//                            byte[] result = App.mPlayerClient.CallCustomFunc(devId, devUserName, devUserPwd, 66052, request);
//                            ALog.e("result-->" + new String(result));
//
//                            if (result != null) {
//                                DevicePreferencesBean response = JSON.parseObject(new String(result), DevicePreferencesBean.class);
//                                subscriber.onNext(response);
//                            } else {
//                                subscriber.onError(new NullPointerException());
//                            }
//                            subscriber.onCompleted();
//                        }).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(preferences -> {
//                            doorPassword = new String(Base64.decode(preferences.getValue().getDev_Psw(), Base64.DEFAULT));
//                            lockPassword = new String(Base64.decode(preferences.getValue().getLock_Psw(), Base64.DEFAULT));
////                            etDoorbellPassword.setText(doorPassword);
////                            etUnlockPassword.setText(lockPassword);
//                            sbAudio.setChecked(preferences.getValue().getDoor_Audio() == 1);
//                        }, throwable -> {
//                            throwable.printStackTrace();
//                            dismissLoading();
//                            DialogHelper.errorSnackbar(getView(), "获取设备信息失败，请重试！");
//                            pop();
//                        }, this::dismissLoading/*, disposable -> showLoading()*/)
//        );
//    }
//
//    private void setDeviceInfo() {
//        strDoorbell = etDoorbellPassword.getText().toString();
//        strUnlock = etUnlockPassword.getText().toString();
//        if (TextUtils.isEmpty(strDoorbell)) {
//            DialogHelper.errorSnackbar(getView(), "门铃密码不能为空！");
//            return;
//        }
//        if (TextUtils.isEmpty(strUnlock)) {
//            DialogHelper.errorSnackbar(getView(), "开锁密码不能为空！");
//            return;
//        }
//
//        mRxManager.add(Observable.create((Observable.OnSubscribe<String>) e -> {
//                    byte[] byteDoorbell = Base64.encode("admin".getBytes(), Base64.DEFAULT);
//                    byte[] byteUnlock = Base64.encode(strUnlock.getBytes(), Base64.DEFAULT);
//                    strDoorbell = new String(byteDoorbell);
//                    strUnlock = new String(byteUnlock);
//
//                    int openAudio = sbAudio.isChecked() ? 1 : 0;
//                    String request = "{\"Operation\":2,\"Request_Type\":1,\"Value\":{\"Dev_Psw\":\""
//                            + strUnlock
//                            + "\",\"Lock_Psw\":\""
//                            + strUnlock
//                            + "\",\"Pir_State\":"
//                            + 1
//                            + ",\"Pir_Time\":"
//                            + 30
//                            + ",\"Tamper_State\":"
//                            + 1
//                            + ",\"Door_Audio\":"
//                            + openAudio + "}}";
//                    Log.e("request-->", request);
//
//                    byte[] result = App.mPlayerClient.CallCustomFunc(devId, devUserName, devUserPwd, 66052, request.getBytes());
//                    if (result != null) {
//                        e.onNext(new String(result));
//                    } else {
//                        e.onError(new NullPointerException());
//                    }
//                    e.onCompleted();
//                }).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(s -> {
//                            ALog.e("s-->" + s);
//                            DialogHelper.successSnackbar(getView(), "设置成功！");
//                        }, throwable -> {
//                            throwable.printStackTrace();
//                            dismissLoading();
//                            DialogHelper.errorSnackbar(getView(), "设置失败，请重试！");
//                        }, this::dismissLoading/*, disposable -> showLoading()*/)
//        );
//    }
//
//    //解析设备参数，如果已经解析过，再次点击播放的时候就不需要解析。
//    private void parseParams() {
//        if (device == null) {
//            DialogHelper.errorSnackbar(getView(), "找不到设备信息！");
//            return;
//        }
//
//        if (params != null) {
//            return;
//        }
//        ALog.e("device.conn_params-->" + device.conn_params);
//        //解析参数
//        params = device.conn_params.split(",");
//        devUserName = "";
//        devId = "";
//        devUserPwd = "";
//
//        for (int i = 0; i < params.length; i++) {
//            if (params[i].contains("UserName")) {
//                devUserName = params[i].split("=")[1];
//                continue;
//            }
//            if (params[i].contains("UserPwd")) {
//                devUserPwd = params[i].split("=")[1];
//                continue;
//            }
//            if (params[i].contains("DevId")) {
//                devId = params[i].split("=")[1];
//            }
//        }
//        ALog.e(devUserName);
//        ALog.e(devId);
//        ALog.e(devUserPwd);
//    }
//}
