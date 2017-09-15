package com.aglhz.s1.room.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface RoomContract {

    interface View extends BaseContract.View {
        void responseRoomInfo(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestRoomInfo(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestRoomInfo(Params params);
    }
}