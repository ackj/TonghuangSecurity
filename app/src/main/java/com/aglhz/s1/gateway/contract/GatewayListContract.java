package com.aglhz.s1.gateway.contract;

import com.aglhz.s1.bean.GatewaysBean;
import com.aglhz.s1.common.Params;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface GatewayListContract { 

    interface View extends BaseContract.View {
        void responsegatewayList(List<GatewaysBean.DataBean> bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestgatewayList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<GatewaysBean> requestgatewayList(Params params);
    }
}