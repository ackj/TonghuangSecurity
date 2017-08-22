package com.aglhz.s1.room.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.room.contract.AddDeviceContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AddDeviceModel extends BaseModel implements AddDeviceContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<BaseBean> requestnewDevice(Params params) {
        return HttpHelper.getService(ApiService.class).requestNewDevice(ApiService.requestNewDevice
                , params.token,params.deviceType,params.name,params.roomFid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestModDevice(Params params) {
        return HttpHelper.getService(ApiService.class).requestModDevice(ApiService.requestModDevice
                , params.token,params.index,params.name,params.roomFid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDelDevice(Params params) {
        return HttpHelper.getService(ApiService.class).requestDelDevice(ApiService.requestDelDevice
                , params.token,params.index)
                .subscribeOn(Schedulers.io());
    }

}