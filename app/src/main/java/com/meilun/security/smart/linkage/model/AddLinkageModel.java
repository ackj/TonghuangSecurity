package com.meilun.security.smart.linkage.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.DeviceListBean;
import com.meilun.security.smart.entity.bean.SceneBean;
import com.meilun.security.smart.entity.bean.SecurityBean;
import com.meilun.security.smart.linkage.contract.AddLinkageContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/8/28 0028 10:34.
 * Email: liujia95me@126.com
 */

public class AddLinkageModel extends BaseModel implements AddLinkageContract.Model{

    private static final String TAG = AddLinkageModel.class.getSimpleName();

    @Override
    public Observable<BaseBean> requestNewLinkage(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestNewlinkage(ApiService.requestNewlinkage,
                        params.token,
                        params.name,
                        params.triggerType,
                        params.cdt_sensorId,
                        params.cdt_sensorAct,
                        params.cdt_day,
                        params.cdt_time,
                        params.targetType,
                        params.targetId,
                        params.nodeId,
                        params.act1,
                        params.delay,
                        params.act2)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SceneBean> requestSceneList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSceneList(ApiService.requestSceneList,
                        params.pageSize,
                        params.page,
                        params.token)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<DeviceListBean> requestDeviceList(Params params) {
        return HttpHelper.getService(ApiService.class).requestDeviceNotAlone(ApiService.requestDeviceNotAlone
                , params.token)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SecurityBean> requestSecurityList(Params params) {
        return HttpHelper.getService(ApiService.class).requestSecurity(ApiService.requestSecurity
                , params.token)
                .subscribeOn(Schedulers.io());
    }
}
