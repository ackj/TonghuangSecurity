package com.meilun.security.smart.host.contract;

import com.meilun.security.smart.entity.bean.GatewaysBean;
import com.meilun.security.smart.common.Params;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface HostListContract {

    interface View extends BaseContract.View {
        void responseGateways(List<GatewaysBean.DataBean> bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestGateways(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<GatewaysBean> requestGateways(Params params);
    }
}