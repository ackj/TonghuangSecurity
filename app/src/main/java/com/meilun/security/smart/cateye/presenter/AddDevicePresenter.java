package com.meilun.security.smart.cateye.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.cateye.contract.AddDeviceContract;
import com.meilun.security.smart.cateye.model.AddDeviceModel;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/10/25 0025 11:44.
 * Email: liujia95me@126.com
 */

public class AddDevicePresenter extends BasePresenter<AddDeviceContract.View,AddDeviceContract.Model> implements AddDeviceContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public AddDevicePresenter(AddDeviceContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddDeviceContract.Model createModel() {
        return new AddDeviceModel();
    }

    @Override
    public void requestAddDevice(Params params) {
        mRxManager.add(mModel.requestAddDevice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseAddSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
