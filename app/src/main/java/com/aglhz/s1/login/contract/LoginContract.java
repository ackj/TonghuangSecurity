package com.aglhz.s1.login.contract;


import com.aglhz.s1.bean.UserBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

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

        void requestRegisterUMeng(String alias);
    }
}