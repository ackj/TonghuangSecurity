package com.aglhz.s1.more.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.more.contract.AddHostContract;
import com.aglhz.s1.more.model.AddHostModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
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
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseAddHost(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start(null))
        );
    }
}