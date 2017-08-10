package com.aglhz.s1.gateway.model;

import com.aglhz.s1.bean.GatewaysBean;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.gateway.contract.GatewayListContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class GatewayListModel extends BaseModel implements GatewayListContract.Model {
    @Override
    public void start(Object request) {

    }

    public Observable<GatewaysBean> requestGateways(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestGateways(ApiService.requestGateways,
                        params.test_token,
                        params.pageSize,
                        params.page)
                .subscribeOn(Schedulers.io());
    }

}