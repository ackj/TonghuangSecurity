package com.aglhz.s1.security.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.security.contract.SecurityContract;
import com.aglhz.s1.security.model.SecurityModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/7/4 0004 09:36.
 * Email: liujia95me@126.com
 */

public class SecurityPresenter extends BasePresenter<SecurityContract.View, SecurityContract.Model> implements SecurityContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public SecurityPresenter(SecurityContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected SecurityContract.Model createModel() {
        return new SecurityModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestSecurity(Params params) {
        mRxManager.add(mModel.requestSecurity(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(securityBean -> {
                    //todo:update
//                    if (securityBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseSecurity(securityBean);
//                    } else {
//                        getView().error(securityBean.getOther().getMessage());
//                    }
                }, this::error));
    }

    @Override
    public void requestHostList(Params params) {
        mRxManager.add(mModel.requestHostList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hostListBean -> {
                    //todo:update
                }, this::error));
    }

    @Override
    public void responseChangedHostStatus(Params params) {
        mRxManager.add(mModel.responseChangedHostStatus(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    //todo:update
                }, this::error));
    }
}
