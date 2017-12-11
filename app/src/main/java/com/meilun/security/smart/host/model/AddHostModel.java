package com.meilun.security.smart.host.model;


import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.host.contract.AddHostContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责添加主机模块的Model层内容。
 */

public class AddHostModel extends BaseModel implements AddHostContract.Model {
    private final String TAG = AddHostModel.class.getSimpleName();

    @Override
    public Observable<BaseBean> requestAddHost(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestAddHost(ApiService.requestAddHost,
                        params.token,
                        params.gateway,
                        params.name,
                        params.roomDir,
                        params.addr,
                        params.addrDet,
                        params.lng,
                        params.lat)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestEditHostLocation(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestEditHostLocation(ApiService.requestEditHostLocation,
                        params.token,
                        params.addr,
                        params.addrDet,
                        params.lng,
                        params.lat,
                        params.gateway)
                .subscribeOn(Schedulers.io());
    }
}