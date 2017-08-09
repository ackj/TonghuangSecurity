package com.aglhz.s1.gateway.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.gateway.contract.GatewayListContract;
import com.aglhz.s1.gateway.model.GatewayListModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class GatewayListPresenter extends BasePresenter<GatewayListContract.View, GatewayListContract.Model> implements GatewayListContract.Presenter {
    private final String TAG = GatewayListPresenter.class.getSimpleName();

    public GatewayListPresenter(GatewayListContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected GatewayListContract.Model createModel() {
        return new GatewayListModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestgatewayList(Params params) {
        mRxManager.add(mModel.requestgatewayList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responsegatewayList(bean.getData());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }
}