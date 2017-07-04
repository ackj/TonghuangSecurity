package com.aglhz.s1.security.model;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.bean.HostListBean;
import com.aglhz.s1.bean.SecurityBean;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.security.contract.SecurityContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/7/4 0004 09:19.
 * Email: liujia95me@126.com
 */

public class SecurityModel extends BaseModel implements SecurityContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<SecurityBean> requestSecurity(Params params) {
        return HttpHelper.getService(ApiService.class).requestSecurity(ApiService.requestSecurity)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<HostListBean> requestHostList(Params params) {
        return HttpHelper.getService(ApiService.class).requestHostList(ApiService.requestHostList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> responseChangedHostStatus(Params params) {
        return HttpHelper.getService(ApiService.class).requestChangedHostStatus(ApiService.requestChangedHostStatus)
                .subscribeOn(Schedulers.io());
    }

}
