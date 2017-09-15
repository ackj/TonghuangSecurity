package com.aglhz.s1.host.model;


import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.host.contract.AddHostContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


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
                        params.no,
                        params.name,
                        params.addr,
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