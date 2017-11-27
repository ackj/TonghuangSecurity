package com.meilun.security.smart.room.contract;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;

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