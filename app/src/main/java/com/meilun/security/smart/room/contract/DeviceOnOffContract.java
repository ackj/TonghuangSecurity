package com.meilun.security.smart.room.contract;

import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

/**
 * Author: LiuJia on 2017/8/30 0030 10:05.
 * Email: liujia95me@126.com
 */

public interface DeviceOnOffContract {

    interface View extends BaseContract.View {
        void responseOnOffSuccess(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestDeviceCtrl(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestDeviceCtrl(Params params);
    }

}
