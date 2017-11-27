package com.aglhz.s1.discover.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.discover.contract.NewsContract;
import com.aglhz.s1.entity.bean.NewsBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

public class NewsModel extends BaseModel implements NewsContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<NewsBean> requestNewsList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestNewsList(ApiService.requestNewsList,params.token,params.page,params.pageSize)
                .subscribeOn(Schedulers.io());
    }
}