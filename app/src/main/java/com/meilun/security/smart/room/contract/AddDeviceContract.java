package com.meilun.security.smart.room.contract;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.RoomsBean;
import com.meilun.security.smart.common.Params;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface AddDeviceContract {

    interface View extends BaseContract.View {
        void responseSuccess(BaseBean bean);
        void responseHouseList(List<RoomsBean.DataBean.RoomListBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestnewDevice(Params params);
        void requestModDevice(Params params);
        void requestDelDevice(Params params);
        void requestHouseList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestnewDevice(Params params);
        Observable<BaseBean> requestModDevice(Params params);
        Observable<BaseBean> requestDelDevice(Params params);
        Observable<RoomsBean> requestHouseList(Params params);
    }
}