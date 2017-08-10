package com.aglhz.s1.gateway.contract;

import com.aglhz.s1.bean.GatewaysBean;
import com.aglhz.s1.common.Params;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

public interface GatewayListContract {

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