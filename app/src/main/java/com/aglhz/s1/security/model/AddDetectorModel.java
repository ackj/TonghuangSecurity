package com.aglhz.s1.security.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DevicesBean;
import com.aglhz.s1.security.contract.AddDetectorContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AddDetectorModel extends BaseModel implements AddDetectorContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<DevicesBean> requestDetectorList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSensorTypeList(ApiService.requestSensorTypeList,
                        params.token,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestAddDetector(Params params) {
        return HttpHelper.getService(ApiService.class)
                .reqeuestNewsensor(ApiService.reqeuestNewsensor,
                        params.token,
                        params.sensorType,
                        params.name,
                        params.defenseLevel,
                        params.roomFid)
                .subscribeOn(Schedulers.io());
    }

}