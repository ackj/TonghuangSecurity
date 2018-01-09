//package com.meilun.security.smart.cateye.view;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.util.Base64;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.Player.web.response.DevItemInfo;
//import com.Player.web.response.ResponseDevList;
//import com.Player.web.response.ResponseDevState;
//import com.Player.web.websocket.ClientCore;
//import com.alibaba.fastjson.JSON;
//import com.meilun.security.smart.App;
//import com.meilun.security.smart.R;
//import com.meilun.security.smart.entity.bean.ResultBean;
//import com.meilun.security.smart.widget.PtrHTFrameLayout;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import cn.itsite.abase.common.DialogHelper;
//import cn.itsite.abase.log.ALog;
//import cn.itsite.abase.mvp.view.base.BaseFragment;
//import cn.itsite.statemanager.StateLayout;
//
//
///**
// * Author: LiuJia on 2017/5/4 0004 11:34.
// * Email: liujia95me@126.com
// */
//
//public class DeviceFragment extends BaseFragment {
//    public static final String TAG = DeviceFragment.class.getSimpleName();
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.toolbar_menu)
//    TextView toolbarMenu;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    @BindView(R.id.stateLayout)
//    StateLayout stateLayout;
//    @BindView(R.id.ptrFrameLayout)
//    PtrHTFrameLayout ptrFrameLayout;
//    Unbinder unbinder;
//    private DeviceRVAdapter adapter;
//    public List<String> deviceUmids = new ArrayList<>();
//    public List<DevItemInfo> devices;
//
//    public static DeviceFragment newInstance() {
//        return new DeviceFragment();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_list, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        EventBus.getDefault().register(this);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initToolbar();
//        initData();
//        initListener();
//    }
//
//    private void initToolbar() {
//        initStateBar(toolbar);
//        toolbarTitle.setText("猫眼管理");
//        toolbarMenu.setText("添加猫眼");
//        toolbarMenu.setOnClickListener(v -> _mActivity.start(AddDeviceFragment.newInstance()));
//    }
//
//    private void initData() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
//        adapter = new DeviceRVAdapter();
//        recyclerView.setAdapter(adapter);
//        initPtrFrameLayout(ptrFrameLayout, recyclerView);
//    }
//
//    @Override
//    public void onRefresh() {
//        ClientCore.getInstance().getNodeList(false, 0, 0, 100, new Handler() {
//            @SuppressLint("HandlerLeak")
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                ResponseDevList response = (ResponseDevList) msg.obj;
//                if ((response != null) && (response.h != null) && (response.h.e == 200)) {
//                    ALog.e("responseCommon.h.e-->" + response.h.e);
//                    //成功获取到状态
//                    deviceUmids.clear();
//                    App.devices = devices = ((ResponseDevList) msg.obj).b.nodes;
//                    EventBus.getDefault().post(new DeviceEvent());
//                    for (DevItemInfo node : devices) {
//                        deviceUmids.add(node.umid);
//                    }
//                    getDeviceStates();
//                } else {
//                    //获取设备失败
//                    DialogHelper.errorSnackbar(getView(), "获取猫眼失败！");
//                    ptrFrameLayout.refreshComplete();
//                }
//            }
//        });
//    }
//
//    private void initListener() {
//        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
//            switch (view.getId()) {
//                case R.id.cv_item_device:
//                    _mActivity.start(MonitorFragment.newInstance(devices.get(position)));
//                    break;
//                case R.id.iv_key_item_device:
//                    unLock(adapter.getData().get(position).umid);
//                    break;
//                case R.id.iv_setup_item_device:
//                    _mActivity.start(DeviceSettingsFragment.newInstance(devices.get(position)));
//                    break;
//                default:
//            }
//            return false;
//        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//        EventBus.getDefault().unregister(this);
//    }
//
//    private void getDeviceStates() {
//        ClientCore.getInstance().getDevState(deviceUmids, new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                ptrFrameLayout.refreshComplete();
//                ResponseDevState response = (ResponseDevState) msg.obj;
//                if ((response != null) && (response.h != null) && (response.h.e == 200)) {
//                    ALog.e("responseCommon.h.e-->" + response.h.e);
//                    //成功获取到状态
//                    ALog.e("state-->" + response.b.devs.size());
//
//                    if (adapter != null) {
//                        adapter.setDeviceStates(response.b.devs);
//                        adapter.setNewData(devices);
//                    }
//
//                } else {
//                    //获取状态失败
//                    DialogHelper.errorSnackbar(getView(), "获取猫眼失败！");
//                    ptrFrameLayout.refreshComplete();
//                }
//            }
//        });
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onRefreshEvent(EventRefreshDeviceList event) {
//        ptrFrameLayout.autoRefresh();
//    }
//
//
//    /**
//     * “ztca”开头umid以外 的设备 解锁
//     *
//     * @param password
//     */
//    public void openLock2(String umid, String password) {
//        showLoading();
//        new Thread() {
//
//            @Override
//            public void run() {
//                String passwordBase64 = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
//                String requestStr = "{\"Operation\":3,\"Request_Type\":0,\"Value\":{\"Lock_Psw\":\""
//                        + passwordBase64 + "\"}}";
//                byte[] request = requestStr.getBytes();
//                ALog.e("openLock-->" + requestStr);
//                byte[] result = App.mPlayerClient.CallCustomFunc(umid, "admin", "admin", 66052, request);
//                dismissLoading();
//                if (null != result) {
//                    String sResult = new String(result);
//
//                    ALog.e("openLock-->" + sResult);
//                    final ResultBean bean = JSON.parseObject(sResult, ResultBean.class);
//                    recyclerView.post(() -> {
//                        if (bean.getResult() == 1) {
//                            DialogHelper.successSnackbar(getView(), "恭喜，开锁成功！");
//                        } else {
//                            DialogHelper.errorSnackbar(getView(), "抱歉，开锁失败！");
//                        }
//                    });
//
//                    Log.e("openLock", sResult);
//                } else {
//                    DialogHelper.errorSnackbar(getView(), "抱歉，开锁失败！");
//
//                }
//            }
//        }.start();
//
//    }
//
//    /**
//     * 输入密码解锁
//     *
//     * @param umid
//     */
//    public void unLock(String umid) {
//        EditText passEditText = new EditText(_mActivity);
//        new AlertDialog.Builder(_mActivity)
//                .setTitle("请输入密码")
//                .setView(passEditText)
//                .setPositiveButton(R.string.positive, (dialog, which) -> {
//                    String inputText = passEditText.getText().toString();
//                    if (TextUtils.isEmpty(inputText)) {
//                        DialogHelper.warningSnackbar(getView(), "密码不能为空！");
//                        return;
//                    }
//                    openLock2(umid, inputText);
//                    dialog.cancel();
//                }).setNegativeButton(R.string.negative, null)
//                .show();
//    }
//}
