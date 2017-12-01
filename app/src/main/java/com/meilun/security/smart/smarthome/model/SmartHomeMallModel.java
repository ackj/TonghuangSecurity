package com.meilun.security.smart.smarthome.model;


import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.GoodsBean;
import com.meilun.security.smart.entity.bean.SubCategoryBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.smarthome.contract.SmartHomeMallContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Author: LiuJia on 2017/5/22 0022 09:07.
 * Email: liujia95me@126.com
 */

public class SmartHomeMallModel extends BaseModel implements SmartHomeMallContract.Model {

    @Override
    public Observable<SubCategoryBean> requestSubCategoryList(Params params) {
        return HttpHelper.getService(ApiService.class).requestSubCategoryLevel(
                ApiService.requestSubCategoryLevel,params.token,params.appType,params.id)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GoodsBean> requestGoodsList(Params params) {
        return HttpHelper.getService(ApiService.class).requestGoodsList(
                ApiService.requestGoodsList,params.token,params.appType,params.secondCategoryId)
                .subscribeOn(Schedulers.io());
    }
}
