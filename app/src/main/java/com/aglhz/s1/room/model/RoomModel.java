package com.aglhz.s1.room.model;

import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.room.contract.RoomContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RoomModel extends BaseModel implements RoomContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<BaseBean> requestRoomInfo(Params params) {
        return HttpHelper.getService(ApiService.class).requestRoomInfo(ApiService.requestRoomInfo
                )
                .subscribeOn(Schedulers.io());
    }

}