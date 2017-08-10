package com.aglhz.s1.scene.model;

import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.scene.contract.IntelligenceLinkageContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IntelligenceLinkageModel extends BaseModel implements IntelligenceLinkageContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<BaseBean> requestLinkageList(Params params) {
        return HttpHelper.getService(ApiService.class).requestLinkageList(ApiService.requestLinkageList
                )
                .subscribeOn(Schedulers.io());
    }
	@Override
    public Observable<BaseBean> requestLinkageSwitch(Params params) {
        return HttpHelper.getService(ApiService.class).requestLinkageSwitch(ApiService.requestLinkageSwitch
                )
                .subscribeOn(Schedulers.io());
    }
	@Override
    public Observable<BaseBean> requestDeleteLinkage(Params params) {
        return HttpHelper.getService(ApiService.class).requestDeleteLinkage(ApiService.requestDeleteLinkage
                )
                .subscribeOn(Schedulers.io());
    }

}