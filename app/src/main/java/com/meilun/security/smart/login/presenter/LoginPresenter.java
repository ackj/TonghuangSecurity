package com.meilun.security.smart.login.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.UserHelper;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.login.contract.LoginContract;
import com.meilun.security.smart.login.model.LoginModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(LoginContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }

    @Override
    public void start(Object request) {
        Params params = (Params) request;

        mRxManager.add(mModel.requestLogin(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userBean -> {
                    if (userBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        //保存用户信息
                        UserHelper.setAccount(params.user, params.pwd);//setAccount要先于setUserInfo调用，不然无法切换SP文件。
                        UserHelper.setUserInfo(userBean.getData().getMemberInfo());
                        Params.token = UserHelper.token;//必须赋值一次。
                        //注册友盟
                        mModel.registerPush();
                        getView().start(null);
                    } else {
                        getView().error(userBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}