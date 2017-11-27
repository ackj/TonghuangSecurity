package com.aglhz.s1.discover.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.NewsBean;

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
