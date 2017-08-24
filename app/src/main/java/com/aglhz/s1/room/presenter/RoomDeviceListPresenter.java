package com.aglhz.s1.room.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.room.contract.RoomDeviceListContract;
import com.aglhz.s1.room.model.RoomDeviceListModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/8/20 0020 18:26.
 * Email: liujia95me@126.com
 */

public class RoomDeviceListPresenter extends BasePresenter<RoomDeviceListContract.View, RoomDeviceListContract.Model> implements RoomDeviceListContract.Presenter{
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public RoomDeviceListPresenter(RoomDeviceListContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RoomDeviceListContract.Model createModel() {
        return new RoomDeviceListModel();
    }


    @Override
    public void requestDeviceList(Params params) {
        mRxManager.add(mModel.requestDeviceList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseDeviceList(bean.getData().getSubDevices());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestHouseList(Params params) {
        mRxManager.add(mModel.requestHouseList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseHouseList(bean.getData().getRoomList());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestAddDevice(Params params) {
        mRxManager.add(mModel.requestAddDevice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseAddDevice(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }
}
