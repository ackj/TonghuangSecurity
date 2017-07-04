package com.aglhz.s1.room.contract;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
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