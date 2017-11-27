package com.meilun.security.smart.discover.contract;

import com.meilun.security.smart.entity.bean.NewsBean;
import com.meilun.security.smart.common.Params;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface NewsContract {

    interface View extends BaseContract.View {
        void responseNewsList(List<NewsBean.DataBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestNewsList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<NewsBean> requestNewsList(Params params);
    }
}
