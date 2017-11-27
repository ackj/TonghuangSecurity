package com.meilun.security.smart.more.model;


import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.more.contract.MoreContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class MoreModel extends BaseModel implements MoreContract.Model {
    private final String TAG = MoreModel.class.getSimpleName();

    @Override
    public Observable<BaseBean> requestLogout(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestLogout(ApiService.requestLogout, params.token)
                .subscribeOn(Schedulers.io());
    }
}