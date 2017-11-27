package com.meilun.security.smart.room.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.room.contract.DeviceTypeContract;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DevicesBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/8/30 0030 20:06.
 * Email: liujia95me@126.com
 */

public class DeviceTypeModel extends BaseModel implements DeviceTypeContract.Model {

    @Override
    public Observable<DevicesBean> requestDeviceType(Params params) {
        return HttpHelper.getService(ApiService.class).requestCtrlSDeviceTypeList(ApiService.requestCtrlSDeviceTypeList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestAddDevice(Params params) {
        return HttpHelper.getService(ApiService.class).requestNewDevice(ApiService.requestNewDevice
                , params.token,params.deviceType,params.name,params.roomFid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestAddCamera(Params params) {
        return HttpHelper.getService(ApiService.class).requestNewCamera(ApiService.requestNewDevice
                , params.token, params.deviceType, params.name, params.roomFid, params.deviceId, params.devicePassword)
                .subscribeOn(Schedulers.io());
    }

}
