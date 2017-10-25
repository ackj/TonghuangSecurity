package com.aglhz.s1.history.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.history.contract.DeviceLogsContract;
import com.aglhz.s1.history.model.DeviceLogsModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

public class DeviceLogsPresenter extends BasePresenter<DeviceLogsContract.View, DeviceLogsContract.Model>
        implements DeviceLogsContract.Presenter {
    public final String TAG = DeviceLogsPresenter.class.getSimpleName();

    public DeviceLogsPresenter(DeviceLogsContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected DeviceLogsContract.Model createModel() {
        return new DeviceLogsModel();
    }

    @Override
    public void requestDeviceLogs(Params params) {
//        mRxManager.add(mModel.requestDeviceLogs(params)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RxSubscriber<DeviceLogBean>() {
//                    @Override
//                    public void _onNext(DeviceLogBean bean) {
//                        if (bean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
//                            getView().responseDeviceLogs(bean.getData().getLogs());
//                        } else {
//                            getView().error(bean.getOther().getMessage());
//                        }
//                    }
//                }));

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
}