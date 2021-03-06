package com.meilun.security.smart.linkage.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.linkage.contract.AddLinkageContract;
import com.meilun.security.smart.linkage.model.AddLinkageModel;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/8/28 0028 10:49.
 * Email: liujia95me@126.com
 */

public class AddLinkagePresenter extends BasePresenter<AddLinkageContract.View,AddLinkageContract.Model> implements AddLinkageContract.Presenter{

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public AddLinkagePresenter(AddLinkageContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddLinkageContract.Model createModel() {
        return new AddLinkageModel();
    }


    @Override
    public void requestNewLinkage(Params params) {
        mRxManager.add(mModel.requestNewLinkage(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseAddSuccess(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error/*, this::complete, disposable -> start(null)*/)
        );
    }

    @Override
    public void requestSceneList(Params params) {
        mRxManager.add(mModel.requestSceneList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseSceneList(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error/*, this::complete, disposable -> start(null)*/)
        );
    }

    @Override
    public void requestDeviceList(Params params) {
        mRxManager.add(mModel.requestDeviceList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseDeviceList(bean.getData().getSubDevices());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error/*, this::complete, disposable -> start(null)*/)
        );
    }

    @Override
    public void requestSecurityList(Params params) {
        mRxManager.add(mModel.requestSecurityList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseSecurityList(bean.getData().getSubDevices());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error/*, this::complete, disposable -> start(null)*/)
        );
    }
}
