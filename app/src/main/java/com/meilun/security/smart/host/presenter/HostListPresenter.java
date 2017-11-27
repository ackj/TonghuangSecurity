package com.meilun.security.smart.host.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.host.contract.HostListContract;
import com.meilun.security.smart.host.model.HostListModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

public class HostListPresenter extends BasePresenter<HostListContract.View, HostListContract.Model> implements HostListContract.Presenter {
    private final String TAG = HostListPresenter.class.getSimpleName();

    public HostListPresenter(HostListContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected HostListContract.Model createModel() {
        return new HostListModel();
    }

    @Override
    public void requestGateways(Params params) {
        mRxManager.add(mModel.requestGateways(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseGateways(bean.getData());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }
}