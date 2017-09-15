package com.aglhz.s1.login.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.login.contract.ResetPasswordContract;
import com.aglhz.s1.login.model.ResetPasswordModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class ResetPasswordPresenter extends BasePresenter<ResetPasswordContract.View,ResetPasswordContract.Model> implements ResetPasswordContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public ResetPasswordPresenter(ResetPasswordContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ResetPasswordContract.Model createModel() {
        return new ResetPasswordModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestReset(Params params) {
        mRxManager.add(mModel.requestReset(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().reponseResetSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestVerifyCode(Params params) {
        mRxManager.add(mModel.requestVerifyCode(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseVerfyCodeSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
