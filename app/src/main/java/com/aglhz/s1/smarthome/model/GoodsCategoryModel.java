package com.aglhz.s1.smarthome.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.FirstLevelBean;
import com.aglhz.s1.smarthome.contract.GoodsCategoryContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Author: LiuJia on 2017/5/22 0022 10:22.
 * Email: liujia95me@126.com
 */

public class GoodsCategoryModel extends BaseModel implements GoodsCategoryContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<FirstLevelBean> requestFirstLevel(Params params) {
        return HttpHelper.getService(ApiService.class).requestFirstLevel(ApiService.requestFirstLevel,params.keywords)
                .subscribeOn(Schedulers.io());
    }
}
