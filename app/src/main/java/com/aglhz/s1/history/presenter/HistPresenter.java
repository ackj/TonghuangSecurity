package com.aglhz.s1.history.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.history.contract.HistContract;
import com.aglhz.s1.history.model.HistModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
public class HistPresenter extends BasePresenter<HistContract.View, HistContract.Model> implements HistContract.Presenter {
    private final String TAG = HistPresenter.class.getSimpleName();

    public HistPresenter(HistContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected HistContract.Model createModel() {
        return new HistModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestHistory(Params params) {
        mRxManager.add(mModel.requestHistory(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseHistory(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }

    
}