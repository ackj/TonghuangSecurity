package com.meilun.security.smart.discover.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.discover.contract.DiscoverContract;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.discover.model.DiscoverModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/11/23 0023 10:46.
 * Email: liujia95me@126.com
 */

public class DiscoverPresenter extends BasePresenter<DiscoverContract.View, DiscoverContract.Model> implements DiscoverContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public DiscoverPresenter(DiscoverContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected DiscoverContract.Model createModel() {
        return new DiscoverModel();
    }

    @Override
    public void requestFirstLevel(Params params) {
        mRxManager.add(mModel.requestFirstLevel(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(firstLevelBean -> {
                    if (firstLevelBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseFirstLevel(firstLevelBean.getData());
                    } else {
                        getView().error(firstLevelBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestSubCategoryList(Params params) {
        mRxManager.add(mModel.requestSubCategoryList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryBean -> {
                    if (categoryBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseSubCategoryList(categoryBean.getData());
                    } else {
                        getView().error(categoryBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestDeviceLogs(Params params) {
        mRxManager.add(mModel.requestDeviceLogs(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseDeviceLogs(bean.getData().getLogs());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }));
    }

    @Override
    public void requestDiscoverPage(Params params) {
        mRxManager.add(mModel.requestDiscoverPage(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseDiscoverPage(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }));
    }

    @Override
    public void requestSwichState(Params params) {
        mRxManager.add(mModel.requestSwichState(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void _onNext(BaseBean baseBean) {
                        if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                            getView().responseSwichState(baseBean);
                        } else {
                            getView().error(baseBean.getOther().getMessage());
                        }
                    }
                }));
    }
}
