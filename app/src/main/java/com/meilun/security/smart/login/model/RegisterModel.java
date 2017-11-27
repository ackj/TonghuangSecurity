package com.meilun.security.smart.login.model;


import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.login.contract.RegisterContract;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class RegisterModel extends BaseModel implements RegisterContract.Model {

    @Override
    public Observable<BaseBean> register(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestRegister(ApiService.requestRegister,
                        params.sc,
                        params.account,
                        params.verifyCode,
                        params.password1,
                        params.password2)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestVerifyCode(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestVerifyCode(ApiService.requestVerifyCode,
                        params.sc,
                        params.phoneNo,
                        params.verifyType)
                .subscribeOn(Schedulers.io());
    }
}
