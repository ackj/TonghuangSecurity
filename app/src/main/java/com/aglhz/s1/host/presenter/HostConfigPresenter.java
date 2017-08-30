package com.aglhz.s1.host.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.host.contract.HostConfigContract;
import com.aglhz.s1.host.model.HostConfigModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责添加主机模块Presenter层内容。
 */

public class HostConfigPresenter extends BasePresenter<HostConfigContract.View, HostConfigContract.Model> implements HostConfigContract.Presenter {
    private final String TAG = HostConfigPresenter.class.getSimpleName();

    public HostConfigPresenter(HostConfigContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected HostConfigContract.Model createModel() {
        return new HostConfigModel();
    }

    @Override
    public void requestHostConfig(Params params) {
        mRxManager.add(mModel.requestHostConfig(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        getView().responseHostConfig(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start(null))
        );
    }
}