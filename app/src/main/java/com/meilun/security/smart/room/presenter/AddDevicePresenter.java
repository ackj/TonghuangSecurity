package com.meilun.security.smart.room.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.room.contract.AddDeviceContract;
import com.meilun.security.smart.room.model.AddDeviceModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

public class AddDevicePresenter extends BasePresenter<AddDeviceContract.View, AddDeviceContract.Model> implements AddDeviceContract.Presenter {
    private final String TAG = AddDevicePresenter.class.getSimpleName();

    public AddDevicePresenter(AddDeviceContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddDeviceContract.Model createModel() {
        return new AddDeviceModel();
    }

    @Override
    public void requestnewDevice(Params params) {
        mRxManager.add(mModel.requestnewDevice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseSuccess(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestModDevice(Params params) {
        mRxManager.add(mModel.requestModDevice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseSuccess(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestDelDevice(Params params) {
        mRxManager.add(mModel.requestDelDevice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseSuccess(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestHouseList(Params params) {
        mRxManager.add(mModel.requestHouseList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseHouseList(bean.getData().getRoomList());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

}