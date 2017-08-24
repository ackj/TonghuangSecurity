package com.aglhz.s1.scene.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.scene.contract.IntelligenceSceneContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IntelligenceSceneModel extends BaseModel implements IntelligenceSceneContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> requestSceneList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSceneList(ApiService.requestSceneList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestStartScene(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestStartScene(ApiService.requestStartScene)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDeleteScene(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDeleteScene(ApiService.requestDeleteScene)
                .subscribeOn(Schedulers.io());
    }
}