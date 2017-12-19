package com.meilun.security.smart.cateye.model;


import com.meilun.security.smart.cateye.contract.DeviceSettingContract;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import rx.Observable;

/**
 * Author: LiuJia on 2017/10/25 0025 16:43.
 * Email: liujia95me@126.com
 */

public class DeviceSettingModel extends BaseModel implements DeviceSettingContract.Model {
    @Override
    public Observable<BaseBean> requestDelDevice(Params params) {
//        return HttpHelper.getService(ApiService.class)
//                .requestUnBindDevice(ApiService.requestUnBindDevice, params.token, params.serialNumber, params.account)
//                .subscribeOn(Schedulers.io());
        return null;
    }
}
