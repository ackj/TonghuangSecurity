package com.aglhz.s1.login.model;


import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.login.contract.ResetPasswordContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class ResetPasswordModel extends BaseModel implements ResetPasswordContract.Model {

    @Override
    public Observable<BaseBean> requestReset(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestResetPassword(ApiService.requestResetPassword,
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

    @Override
    public void start(Object request) {

    }
}
