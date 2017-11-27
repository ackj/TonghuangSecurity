package com.aglhz.s1.discover.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.discover.contract.NewsContract;
import com.aglhz.s1.discover.model.NewsModel;

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