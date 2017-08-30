package com.aglhz.s1.more.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.AuthorizationBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface AuthorizationContract { 

    interface View extends BaseContract.View {
        void responsegatewayAuthList(List<AuthorizationBean.DataBean> bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestgatewayAuthList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<AuthorizationBean> requestgatewayAuthList(Params params);
    }
}