package com.aglhz.s1.login.contract;


import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/9 0009 19:26.
 * Email: liujia95me@126.com
 */

public interface RegisterContract {

    interface View extends BaseContract.View {

        void registerSuccess(BaseBean baseBean);

        void getVerfyCodeSuccess(BaseBean baseBean);

    }

    interface Presenter extends BaseContract.Presenter {

        void requestRegister(Params params);

        void requestVerifyCode(Params params);
    }

    interface Model extends BaseContract.Model {

        Observable<BaseBean> register(Params params);

        Observable<BaseBean> requestVerifyCode(Params params);
    }
}
