package com.aglhz.s1.room.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface AddDeviceContract { 

    interface View extends BaseContract.View {
        void responseSuccess(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestnewDevice(Params params);
        void requestModDevice(Params params);
        void requestDelDevice(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestnewDevice(Params params);
        Observable<BaseBean> requestModDevice(Params params);
        Observable<BaseBean> requestDelDevice(Params params);
    }
}