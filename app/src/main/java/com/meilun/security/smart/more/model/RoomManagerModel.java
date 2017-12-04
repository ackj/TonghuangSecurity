package com.meilun.security.smart.more.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.RoomsBean;
import com.meilun.security.smart.more.contract.RoomManagerContract;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.RoomTypesBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

public class RoomManagerModel extends BaseModel implements RoomManagerContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<RoomsBean> requestHouseList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestRoomList(ApiService.requestRoomList,
                        params.token,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestAddHouse(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestNewroom(ApiService.requestNewroom,
                        params.token,
                        params.roomName,
                        params.roomTypeFid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDelroom(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDelroom(ApiService.requestDelroom,
                        params.token,
                        params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<RoomTypesBean> requestRoomTypeList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestRoomTypeList(ApiService.requestRoomTypeList,"")
                .subscribeOn(Schedulers.io());
    }

}