package com.meilun.security.smart.cateye.presenter;

import android.support.annotation.NonNull;

import com.meilun.security.smart.cateye.contract.DeviceSettingContract;
import com.meilun.security.smart.cateye.model.DeviceSettingModel;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/10/25 0025 16:43.
 * Email: liujia95me@126.com
 */

public class DeviceSettingPresenter extends BasePresenter<DeviceSettingContract.View,DeviceSettingContract.Model> implements DeviceSettingContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public DeviceSettingPresenter(DeviceSettingContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected DeviceSettingContract.Model createModel() {
        return new DeviceSettingModel();
    }

    @Override
    public void requestDelDevice(Params params) {
        mRxManager.add(mModel.requestDelDevice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseDelSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
