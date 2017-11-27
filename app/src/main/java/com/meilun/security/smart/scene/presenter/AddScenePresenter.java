package com.meilun.security.smart.scene.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.scene.contract.AddSceneContract;
import com.meilun.security.smart.scene.model.AddSceneModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

public class AddScenePresenter extends BasePresenter<AddSceneContract.View, AddSceneContract.Model> implements AddSceneContract.Presenter {
    private final String TAG = AddScenePresenter.class.getSimpleName();

    public AddScenePresenter(AddSceneContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddSceneContract.Model createModel() {
        return new AddSceneModel();
    }

    @Override
    public void requestAddScene(Params params) {
        mRxManager.add(mModel.requestAddScene(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseAddScene(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error/*, this::complete, disposable -> start(null)*/)
        );
    }
}