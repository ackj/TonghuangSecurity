package com.aglhz.s1.more.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.more.contract.AuthorizationContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AuthorizationModel extends BaseModel implements AuthorizationContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<BaseBean> requestgatewayAuthList(Params params) {
        return HttpHelper.getService(ApiService.class).requestGatewayAuthList(ApiService.requestGatewayAuthList
                ,params.token,params.page,params.pageSize)
                .subscribeOn(Schedulers.io());
    }

}