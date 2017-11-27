package com.meilun.security.smart.room.model;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.room.contract.DeviceOnOffContract;
import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/8/30 0030 10:10.
 * Email: liujia95me@126.com
 */

public class DeviceOnOffModel extends BaseModel implements DeviceOnOffContract.Model {

    @Override
    public Observable<BaseBean> requestDeviceCtrl(Params params) {
        return HttpHelper.getService(ApiService.class).requestDevicectrl(ApiService.requestDevicectrl
                , params.token,params.index,params.nodeId,params.status)
                .subscribeOn(Schedulers.io());
    }
}
