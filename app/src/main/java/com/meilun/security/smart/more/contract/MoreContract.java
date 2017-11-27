package com.meilun.security.smart.more.contract;


import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface MoreContract {

    interface View extends BaseContract.View {
        //退出登录成功
        void responseLogout(String message);
    }

    interface Presenter extends BaseContract.Presenter {
        //退出登录
        void requestLogout(Params params);

    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestLogout(Params params);
    }
}