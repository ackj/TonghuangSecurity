package com.aglhz.s1.more.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.more.contract.AuthorizationContract;
import com.aglhz.s1.more.model.AuthorizationModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
public class AuthorizationPresenter extends BasePresenter<AuthorizationContract.View, AuthorizationContract.Model> implements AuthorizationContract.Presenter {
    private final String TAG = AuthorizationPresenter.class.getSimpleName();

    public AuthorizationPresenter(AuthorizationContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AuthorizationContract.Model createModel() {
        return new AuthorizationModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestgatewayAuthList(Params params) {
        mRxManager.add(mModel.requestgatewayAuthList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responsegatewayAuthList(bean.getData());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    
}