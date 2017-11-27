package com.meilun.security.smart.history.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DeviceLogBean;
import com.meilun.security.smart.history.contract.DeviceLogsContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

public class DeviceLogsModel extends BaseModel implements DeviceLogsContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<DeviceLogBean> requestDeviceLogs(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDeviceLogs(ApiService.requestDeviceLogs,params.token,params.page,params.pageSize)
                .subscribeOn(Schedulers.io());
    }

}