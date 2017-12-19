package com.meilun.security.smart.cateye.contract;


import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

/**
 * Author: LiuJia on 2017/10/25 0025 16:43.
 * Email: liujia95me@126.com
 */

public interface DeviceSettingContract {
    interface View extends BaseContract.View {
        void responseDelSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestDelDevice(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestDelDevice(Params params);
    }
}
