package com.aglhz.s1.more.contract;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

public interface RoomManagerContract {

    interface View extends BaseContract.View {
        void responseHouseList(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestHouseList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestHouseList(Params params);
    }
}