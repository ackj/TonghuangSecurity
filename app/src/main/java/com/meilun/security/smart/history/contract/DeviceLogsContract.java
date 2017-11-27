package com.meilun.security.smart.history.contract;

import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DeviceLogBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface DeviceLogsContract {

    interface View extends BaseContract.View {
        void responseDeviceLogs(List<DeviceLogBean.DataBean.LogsBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestDeviceLogs(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<DeviceLogBean> requestDeviceLogs(Params params);
    }
}