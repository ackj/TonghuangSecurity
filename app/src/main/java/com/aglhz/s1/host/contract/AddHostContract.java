package com.aglhz.s1.host.contract;


import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 主机模块所对应的各层对象应有的接口。
 */
public interface AddHostContract {

    interface View extends BaseContract.View {

        void responseAddHost(BaseBean baseBean);

        void responseEditHostLocation(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {

        void requestAddHost(Params params);

        void requestEditHostLocation(Params params);
    }

    interface Model extends BaseContract.Model {

        Observable<BaseBean> requestAddHost(Params params);

        Observable<BaseBean> requestEditHostLocation(Params params);
    }
}