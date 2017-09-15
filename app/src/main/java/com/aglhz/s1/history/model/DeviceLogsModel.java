package com.aglhz.s1.history.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.DeviceLogBean;
import com.aglhz.s1.history.contract.DeviceLogsContract;

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