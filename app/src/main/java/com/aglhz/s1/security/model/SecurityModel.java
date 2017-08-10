package com.aglhz.s1.security.model;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.bean.GatewaysBean;
import com.aglhz.s1.bean.SecurityBean;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.security.contract.SecurityContract;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/7/4 0004 09:19.
 * Email: liujia95me@126.com
 */

public class SecurityModel extends BaseModel implements SecurityContract.Model {
    public static final String TAG = SecurityModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<SecurityBean> requestSecurity(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSecurity(ApiService.requestSecurity, params.test_token)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GatewaysBean> requestGateways(Params params) {
        ALog.e("params-->" + params.token);
        return HttpHelper.getService(ApiService.class)
                .requestGateways(ApiService.requestGateways,
                        params.test_token,
                        params.pageSize,
                        params.page)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestSwichGateway(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSwichGateway(ApiService.requestSwichGateway,
                        params.test_token,
                        params.gateway)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestSwichState(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSwichState(ApiService.requestSwichState,
                        params.test_token,
                        params.dstatus)
                .subscribeOn(Schedulers.io());
    }

}
