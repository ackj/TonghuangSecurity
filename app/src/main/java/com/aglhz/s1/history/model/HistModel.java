package com.aglhz.s1.history.model;

import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.history.contract.HistContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class HistModel extends BaseModel implements HistContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<BaseBean> requestHistory(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestHistory(ApiService.requestHistory)
                .subscribeOn(Schedulers.io());
    }

}