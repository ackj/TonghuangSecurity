package com.meilun.security.smart.cateye.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.Player.Core.PlayerClient;
import com.Player.web.response.ResponseCommon;
import com.Player.web.websocket.ClientCore;
import com.meilun.security.smart.cateye.contract.AddDeviceContract;
import com.meilun.security.smart.cateye.model.AddDeviceModel;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/10/25 0025 11:44.
 * Email: liujia95me@126.com
 */

public class AddDevicePresenter extends BasePresenter<AddDeviceContract.View,AddDeviceContract.Model> implements AddDeviceContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public AddDevicePresenter(AddDeviceContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddDeviceContract.Model createModel() {
        return new AddDeviceModel();
    }

    @Override
    public void requestAddDevice(Params params) {
        mRxManager.add(mModel.requestAddDevice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
//                        getView().responseAddSuccess(baseBean);
                        requestAdd2CateyeServer(params);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @SuppressLint("HandlerLeak")
    public void requestAdd2CateyeServer(Params params){
        ClientCore.getInstance().addNodeInfo(params.deviceName, 0, 2, 2,
                vendorID(params.serialNumber), params.serialNumber, "", 0,
                "admin", params.password, 0, 0, 0, new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        ResponseCommon response = (ResponseCommon) msg.obj;
                        ALog.e(response.h.e);
                        if ((response != null) && (response.h != null) && (response.h.e == 200)) {
                            getView().responseAddSuccess(null);
                        } else {
                            getView().error("抱歉，添加失败!");
                        }
                    }
                });
    }

    int vendorID(String umid) {
        if (umid.length() == 16) {
            return PlayerClient.NPC_D_MON_VENDOR_ID_HZXM;
        }
        if ((umid.substring(0, 2).contains("xm")) || (umid.substring(0, 2).contains("Xm")) || (umid.substring(0, 2).contains("xM"))) {
            return PlayerClient.NPC_D_MON_VENDOR_ID_HZXM;
        }
        return PlayerClient.NPC_D_MON_VENDOR_ID_UMSP;
    }
}
