package com.meilun.security.smart.camera.model;

import com.meilun.security.smart.camera.contract.CameraListContract;
import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.CameraBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/9/15 0015 18:18.
 * Email: liujia95me@126.com
 */

public class CameraListModel extends BaseModel implements CameraListContract.Model {

    @Override
    public Observable<CameraBean> requestCameraList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestCameraList(ApiService.requestCameraList,params.token,params.page,params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestNewCamera(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestNewcamera(ApiService.requestNewcamera,params.token,params.deviceId,params.deviceName,params.devicePassword)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDelCamera(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDelcamera(ApiService.requestDelcamera,params.token,params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestModCamera(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestModCamera(ApiService.requestModCamera,
                        params.token,params.fid,params.deviceType,params.deviceName,params.devicePassword)
                .subscribeOn(Schedulers.io());
    }
}
