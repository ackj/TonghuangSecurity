package com.aglhz.s1.security.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.security.contract.SecurityContract;
import com.aglhz.s1.security.model.SecurityModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/7/4 0004 09:36.
 * Email: liujia95me@126.com
 */

public class SecurityPresenter extends BasePresenter<SecurityContract.View, SecurityContract.Model> implements SecurityContract.Presenter {
    public static final String TAG = SecurityPresenter.class.getSimpleName();

    public SecurityPresenter(SecurityContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected SecurityContract.Model createModel() {
        return new SecurityModel();
    }

    @Override
    public void requestSecurity(Params params) {
        mRxManager.add(mModel.requestSecurity(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(securityBean -> {
                    if (securityBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseSecurity(securityBean);
                    } else {
                        getView().error(securityBean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start(null)));
    }

    @Override
    public void requestGateways(Params params) {
        mRxManager.add(mModel.requestGateways(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gatewaysBean -> {
                    if (gatewaysBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseGateways(gatewaysBean);
                    } else {
                        getView().error(gatewaysBean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start(null))
        );
    }

    @Override
    public void requestSwichGateway(Params params) {
        mRxManager.add(mModel.requestSwichGateway(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseSwichGateway(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start(null))
        );
    }

    @Override
    public void requestSwichState(Params params) {
        mRxManager.add(mModel.requestSwichState(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseSwichState(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start(null))
        );
    }
}
