package com.meilun.security.smart.room.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.Player.web.response.DevItemInfo;
import com.Player.web.response.ResponseDevList;
import com.Player.web.websocket.ClientCore;
import com.meilun.security.smart.App;
import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.MainDeviceBean;
import com.meilun.security.smart.entity.bean.MainDeviceListBean;
import com.meilun.security.smart.room.view.DeviceListFragment;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.HttpHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2018/1/3 0003 10:34.
 * Email: liujia95me@126.com
 */

public class DeviceListPresenter extends BasePresenter {

    DeviceListFragment fragment;

    /**
     * 创建Presenter的时候就绑定View和创建model。
     */
    public DeviceListPresenter(DeviceListFragment fragment) {
        super(fragment);
        this.fragment = fragment;
    }

    public List<String> deviceUmids = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    public void requestCateyeList() {
        ClientCore.getInstance().getNodeList(false, 0, 0, 100, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ResponseDevList response = (ResponseDevList) msg.obj;
                if ((response != null) && (response.h != null) && (response.h.e == 200)) {
                    ALog.e("responseCommon.h.e-->" + response.h.e);
                    //成功获取到状态
                    deviceUmids.clear();
                    List<DevItemInfo> devices = App.devices = ((ResponseDevList) msg.obj).b.nodes;
                    List<MainDeviceBean> data = new ArrayList<>();
                    for (DevItemInfo device : devices) {
                        MainDeviceBean bean = new MainDeviceBean();
                        bean.name = device.node_name;
                        String[] params = device.conn_params.split(",");
                        for (int i = 0; i < params.length; i++) {
                            if (params[i].contains("UserName")) {
                                bean.userName = params[i].split("=")[1];
                                continue;
                            }
                            if (params[i].contains("UserPwd")) {
                                bean.userPwd = params[i].split("=")[1];
                                continue;
                            }
                            if (params[i].contains("DevId")) {
                                bean.deviceId = params[i].split("=")[1];
                            }
                        }
                        bean.cateyeBean = device;
                        data.add(bean);
                    }

                    fragment.responseCateyeDevices(data);
                } else {
                    //获取设备失败
                    fragment.error("获取猫眼失败！");
                }
            }
        });
    }

    public void requestCameraList(Params params) {
        mRxManager.add(HttpHelper.getService(ApiService.class)
                .requestMainDeviceList(ApiService.requestMainDeviceList,
                        Params.token,
                        params.type,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        List<MainDeviceBean> data = new ArrayList<>();
                        for (MainDeviceListBean.DataBean device : bean.getData()) {
                            MainDeviceBean b = new MainDeviceBean();
                            b.deviceId = device.getNo();
                            b.name = device.getName();
                            b.userName = device.getName();
                            b.userPwd = device.getPassword();
                            data.add(b);
                        }
                        fragment.responseCameraList(data);
                    } else {
                        fragment.error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    public void requestNewCamera(Params params) {
        mRxManager.add(HttpHelper.getService(ApiService.class)
                .requestNewMainDevice(ApiService.requestNewMainDevice,
                        Params.token,
                        params.type,
                        params.no,
                        params.name,
                        params.password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        fragment.responseOperatorSuccess("添加摄像头成功");
                    } else {
                        fragment.error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    public void requestDeleteCamera(Params params) {
        mRxManager.add(HttpHelper.getService(ApiService.class)
                .requestDelMainDevice(ApiService.requestDelMainDevice,
                        Params.token,
                        params.type,
                        params.no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        fragment.responseOperatorSuccess("删除摄像头成功");
                    } else {
                        fragment.error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

}
