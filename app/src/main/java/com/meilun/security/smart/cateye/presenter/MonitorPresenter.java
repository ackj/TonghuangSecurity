package com.meilun.security.smart.cateye.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.Player.web.response.DevItemInfo;
import com.Player.web.response.ResponseCommon;
import com.Player.web.websocket.ClientCore;
import com.meilun.security.smart.cateye.view.MonitorFragment;
import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.MainDeviceBean;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.HttpHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2018/1/4 0004 15:58.
 * Email: liujia95me@126.com
 */

public class MonitorPresenter extends BasePresenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param fragment 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public MonitorPresenter(MonitorFragment fragment) {
        super(fragment);
        this.fragment = fragment;
    }

    MonitorFragment fragment;

    public void requestDeleteCatEye(MainDeviceBean device) {
        mRxManager.add(HttpHelper.getService(ApiService.class)
                .requestCatEyeDelete(ApiService.requestCatEyeDelete,
                        Params.token,
                        device.deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        deleteCatEye(device.cateyeBean);
                    } else {
                        fragment.error("删除失败");
//                        DialogHelper.errorSnackbar(getView(), "删除失败");

                    }
                }, this::error));
    }

    @SuppressLint("HandlerLeak")
    private void deleteCatEye(DevItemInfo device) {
        ClientCore.getInstance().deleteNodeInfo(String.valueOf(device.node_id), device.node_type, device.id_type, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ResponseCommon response = (ResponseCommon) msg.obj;
                if ((response != null) && (response.h != null) && (response.h.e == 200)) {
                    ALog.e("response.h.e-->" + response.h.e);
                    fragment.responseDeleteSuccess();
                } else {
                    fragment.error("删除失败");
                }
            }
        });
    }
}
