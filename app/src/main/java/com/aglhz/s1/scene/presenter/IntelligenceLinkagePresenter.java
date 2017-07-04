package com.aglhz.s1.scene.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.scene.contract.IntelligenceLinkageContract;
import com.aglhz.s1.scene.model.IntelligenceLinkageModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
public class IntelligenceLinkagePresenter extends BasePresenter<IntelligenceLinkageContract.View, IntelligenceLinkageContract.Model> implements IntelligenceLinkageContract.Presenter {
    private final String TAG = IntelligenceLinkagePresenter.class.getSimpleName();

    public IntelligenceLinkagePresenter(IntelligenceLinkageContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected IntelligenceLinkageContract.Model createModel() {
        return new IntelligenceLinkageModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestLinkageList(Params params) {
        mRxManager.add(mModel.requestLinkageList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseLinkageList(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }
@Override
    public void requestLinkageSwitch(Params params) {
        mRxManager.add(mModel.requestLinkageSwitch(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
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
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseDeleteLinkage(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }

    
}