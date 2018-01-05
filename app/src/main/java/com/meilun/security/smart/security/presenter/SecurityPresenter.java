package com.meilun.security.smart.security.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.MainDeviceListBean;
import com.meilun.security.smart.security.contract.SecurityContract;
import com.meilun.security.smart.security.model.SecurityModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

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
                    if (securityBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseSecurity(securityBean);
                    } else {
                        getView().error(securityBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestGateways(Params params) {
        mRxManager.add(mModel.requestGateways(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MainDeviceListBean>() {
                    @Override
                    public void _onNext(MainDeviceListBean listBean) {
                        if (listBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                            getView().responseGateways(listBean);
                        } else {
                            getView().error(listBean.getOther().getMessage());
                        }
                    }
                }));
    }

    @Override
    public void requestSwichGateway(Params params) {
        mRxManager.add(mModel.requestSwichGateway(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void _onNext(BaseBean baseBean) {
                        if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                            getView().responseSwichGateway(baseBean);
                        } else {
                            getView().error(baseBean.getOther().getMessage());
                        }
                    }
                }));
    }

    @Override
    public void requestSwichState(Params params) {
        mRxManager.add(mModel.requestSwichState(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void _onNext(BaseBean baseBean) {
                        if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                            getView().responseSwichState(baseBean);
                        } else {
                            getView().error(baseBean.getOther().getMessage());
                        }
                    }
                }));
    }

    @Override
    public void requestLeaveMassge(Params params) {
        mRxManager.add(mModel.requestLeaveMassge(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void _onNext(BaseBean baseBean) {
                        if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                            getView().responseSwichState(baseBean);
                        } else {
                            getView().error(baseBean.getOther().getMessage());
                        }
                    }
                }));
    }
}
