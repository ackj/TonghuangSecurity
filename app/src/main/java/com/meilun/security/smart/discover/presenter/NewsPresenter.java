package com.meilun.security.smart.discover.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.discover.model.NewsModel;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.discover.contract.NewsContract;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

public class NewsPresenter extends BasePresenter<NewsContract.View, NewsContract.Model> implements NewsContract.Presenter {

    private final String TAG = NewsPresenter.class.getSimpleName();

    public NewsPresenter(NewsContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected NewsContract.Model createModel() {
        return new NewsModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestNewsList(Params params) {
        mRxManager.add(mModel.requestNewsList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseNewsList(bean.getData());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }));
    }
}