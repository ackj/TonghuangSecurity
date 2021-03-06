package com.meilun.security.smart.login.contract;


import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.UserBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */
public interface LoginContract {

    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {
    }

    interface Model extends BaseContract.Model {

        Observable<UserBean> requestLogin(Params params);

        void registerPush();
    }
}