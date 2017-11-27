package com.meilun.security.smart.scene.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.SceneBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.scene.contract.SceneListContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

public class SceneListModel extends BaseModel implements SceneListContract.Model {

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
    public Observable<BaseBean> requestStartScene(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestStartScene(ApiService.requestStartScene,
                        params.token,
                        params.index)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDeleteScene(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDeleteScene(ApiService.requestDeleteScene,
                        params.token,
                        params.index)
                .subscribeOn(Schedulers.io());
    }
}