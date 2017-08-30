package com.aglhz.s1.room.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.room.contract.DeviceOnOffContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/8/30 0030 10:10.
 * Email: liujia95me@126.com
 */

public class DeviceOnOffModel extends BaseModel implements DeviceOnOffContract.Model {

    @Override
    public Observable<BaseBean> requestDeviceCtrl(Params params) {
        return HttpHelper.getService(ApiService.class).requestDevicectrl(ApiService.requestDevicectrl
                , params.token,params.index,params.nodeId,params.status)
                .subscribeOn(Schedulers.io());
    }
}
