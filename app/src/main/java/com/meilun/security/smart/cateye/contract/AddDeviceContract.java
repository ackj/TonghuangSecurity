package com.meilun.security.smart.cateye.contract;


import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

/**
 * Author: LiuJia on 2017/10/25 0025 11:18.
 * Email: liujia95me@126.com
 */

public interface AddDeviceContract {
    interface View extends BaseContract.View {
        void responseAddSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestAddDevice(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestAddDevice(Params params);
    }
}
