package com.aglhz.s1.scene.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.scene.contract.LinkageListContract;
import com.aglhz.s1.scene.model.LinkageListModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LinkageListPresenter extends BasePresenter<LinkageListContract.View, LinkageListContract.Model> implements LinkageListContract.Presenter {

    private final String TAG = LinkageListPresenter.class.getSimpleName();

    public LinkageListPresenter(LinkageListContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected LinkageListContract.Model createModel() {
        return new LinkageListModel();
    }

    @Override
    public void requestLinkageList(Params params) {
        mRxManager.add(mModel.requestLinkageList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseLinkageList(bean.getData());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestLinkageSwitch(Params params) {
        mRxManager.add(mModel.requestLinkageSwitch(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == BaseConstants.RESPONSE_CODE_SUCCESS) {
//                        getView().responseLinkageSwitch(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }

    @Override
    public void requestDeleteLinkage(Params params) {
        mRxManager.add(mModel.requestDeleteLinkage(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseDeleteLinkage(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }


}