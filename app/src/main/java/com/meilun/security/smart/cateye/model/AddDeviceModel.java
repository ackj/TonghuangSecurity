package com.meilun.security.smart.cateye.model;

import com.meilun.security.smart.cateye.contract.AddDeviceContract;
import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/10/25 0025 11:36.
 * Email: liujia95me@126.com
 */

public class AddDeviceModel extends BaseModel implements AddDeviceContract.Model {
    @Override
    public Observable<BaseBean> requestAddDevice(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestCatEyeUpdate(ApiService.requestCatEyeUpdate,params.account, params.token, params.serialNumber)
                .subscribeOn(Schedulers.io());
    }
}
