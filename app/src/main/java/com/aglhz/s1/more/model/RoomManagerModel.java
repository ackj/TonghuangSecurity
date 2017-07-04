package com.aglhz.s1.more.model;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.more.contract.RoomManagerContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RoomManagerModel extends BaseModel implements RoomManagerContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<BaseBean> requestHouseList(Params params) {
        return HttpHelper.getService(ApiService.class).requestHouseList(ApiService.requestHouseList
                )
                .subscribeOn(Schedulers.io());
    }

}