package com.meilun.security.smart.host.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.AuthorizationBean;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.host.contract.AuthorizationContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

public class AuthorizationModel extends BaseModel implements AuthorizationContract.Model {

    @Override
    public Observable<AuthorizationBean> requestgatewayAuthList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestGatewayAuthList(ApiService.requestGatewayAuthList,
                        params.token,
                        params.gateway,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestGatewayAuth(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestGatewayAuth(ApiService.requestGatewayAuth,
                        params.token,
                        params.gateway,
                        params.mobile)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestGatewayUnAuth(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestGatewayUnAuth(ApiService.requestGatewayUnAuth,
                        params.token,
                        params.gateway,
                        params.fid)
                .subscribeOn(Schedulers.io());
    }
}