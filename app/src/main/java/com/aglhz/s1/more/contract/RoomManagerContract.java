package com.aglhz.s1.more.contract;

import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.RoomTypesBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.common.Params;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

public interface RoomManagerContract {

    interface View extends BaseContract.View {
        void responseHouseList(List<RoomsBean.DataBean.RoomListBean> data);

        void responseAddHouse(BaseBean bean);

        void responseRoomTypeList(List<RoomTypesBean.DataBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestHouseList(Params params);

        void requestAddHouse(Params params);

        void requestRoomTypeList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<RoomsBean> requestHouseList(Params params);

        Observable<BaseBean> requestAddHouse(Params params);

        Observable<RoomTypesBean> requestRoomTypeList(Params params);
    }
}