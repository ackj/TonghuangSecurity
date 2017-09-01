package com.aglhz.s1.linkage.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.LinkageBean;
import com.aglhz.s1.linkage.contract.LinkageListContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LinkageListModel extends BaseModel implements LinkageListContract.Model {

    @Override
    public Observable<LinkageBean> requestLinkageList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestLinkageList(ApiService.requestLinkageList,
                        params.token,params.page,params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestLinkageSwitch(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestLinkageSwitch(ApiService.requestLinkageSwitch)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDeleteLinkage(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDellinkage(ApiService.requestDellinkage,params.token,params.index)
                .subscribeOn(Schedulers.io());
    }

}