package com.meilun.security.smart.cateye.model;

import com.meilun.security.smart.cateye.contract.AddDeviceContract;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import rx.Observable;

/**
 * Author: LiuJia on 2017/10/25 0025 11:36.
 * Email: liujia95me@126.com
 */

public class AddDeviceModel extends BaseModel implements AddDeviceContract.Model {
    @Override
    public Observable<BaseBean> requestAddDevice(Params params) {
//        return HttpHelper.getService(ApiService.class)
//                .requestBindDevice(ApiService.requestBindDevice, params.token, params.serialNumber, params.account)
//                .subscribeOn(Schedulers.io());
        return null;
    }
}
