package com.meilun.security.smart.scene.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.scene.contract.AddSceneContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

public class AddSceneModel extends BaseModel implements AddSceneContract.Model {


    @Override
    public Observable<BaseBean> requestAddScene(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestAddScene(ApiService.requestAddScene,
                        params.token,
                        params.name,
                        params.paramJson)
                .subscribeOn(Schedulers.io());
    }
}