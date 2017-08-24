package com.aglhz.s1.scene.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.scene.contract.IntelligenceSceneContract;
import com.aglhz.s1.scene.model.IntelligenceSceneModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class IntelligenceScenePresenter extends BasePresenter<IntelligenceSceneContract.View, IntelligenceSceneContract.Model> implements IntelligenceSceneContract.Presenter {
    private final String TAG = IntelligenceScenePresenter.class.getSimpleName();

    public IntelligenceScenePresenter(IntelligenceSceneContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected IntelligenceSceneContract.Model createModel() {
        return new IntelligenceSceneModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestSceneList(Params params) {
        mRxManager.add(mModel.requestSceneList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseSceneList(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }

    @Override
    public void requestStartScene(Params params) {
        mRxManager.add(mModel.requestStartScene(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseStartScene(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }

    @Override
    public void requestDeleteScene(Params params) {
        mRxManager.add(mModel.requestDeleteScene(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseDeleteScene(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }


}