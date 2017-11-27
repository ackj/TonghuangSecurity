package com.meilun.security.smart.host.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.host.contract.AddHostContract;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.host.model.AddHostModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责添加主机模块Presenter层内容。
 */

public class AddHostPresenter extends BasePresenter<AddHostContract.View, AddHostContract.Model> implements AddHostContract.Presenter {
    private final String TAG = AddHostPresenter.class.getSimpleName();

    public AddHostPresenter(AddHostContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddHostContract.Model createModel() {
        return new AddHostModel();
    }

    @Override
    public void requestAddHost(Params params) {
        mRxManager.add(mModel.requestAddHost(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void _onNext(BaseBean bean) {
                        if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                            getView().responseAddHost(bean);
                        } else {
                            getView().error(bean.getOther().getMessage());
                        }
                    }
                }));
    }

    @Override
    public void requestEditHostLocation(Params params) {
        mRxManager.add(mModel.requestEditHostLocation(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void _onNext(BaseBean bean) {
                        if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                            getView().responseEditHostLocation(bean);
                        } else {
                            getView().error(bean.getOther().getMessage());
                        }
                    }
                }));
    }
}