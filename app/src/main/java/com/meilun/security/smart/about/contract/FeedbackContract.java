package com.meilun.security.smart.about.contract;


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
public interface FeedbackContract {

    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {
    }

    interface Model extends BaseContract.Model {
        //请求提交反馈
        Observable<BaseBean> requestSubmitFeedback(Params params);
    }
}