package com.aglhz.s1.host.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.host.contract.HostListContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class HostListModel extends BaseModel implements HostListContract.Model {
    @Override
    public void start(Object request) {

    }

    public Observable<GatewaysBean> requestGateways(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestGateways(ApiService.requestGateways,
                        params.token,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io());
    }

}