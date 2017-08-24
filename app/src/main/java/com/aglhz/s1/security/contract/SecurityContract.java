package com.aglhz.s1.security.contract;

import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.entity.bean.SecurityBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/7/4 0004 09:17.
 * Email: liujia95me@126.com
 */

public interface SecurityContract {

    interface View extends BaseContract.View {

        void responseSecurity(SecurityBean securityBean);

        void responseGateways(GatewaysBean gatewaysBean);

        void responseSwichGateway(BaseBean baseBean);

        void responseSwichState(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {

        void requestSecurity(Params params);

        void requestGateways(Params params);

        void requestSwichGateway(Params params);

        void requestSwichState(Params params);

    }

    interface Model extends BaseContract.Model {

        Observable<SecurityBean> requestSecurity(Params params);

        Observable<GatewaysBean> requestGateways(Params params);

        Observable<BaseBean> requestSwichGateway(Params params);

        Observable<BaseBean> requestSwichState(Params params);
    }
}
