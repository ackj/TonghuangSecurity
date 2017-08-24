package com.aglhz.s1.more.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.RoomTypesBean;
import com.aglhz.s1.entity.bean.RoomsBean;
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
                        params.roomTypeFid,
                        params.residenceFid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<RoomTypesBean> requestRoomTypeList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestRoomTypeList(ApiService.requestRoomTypeList)
                .subscribeOn(Schedulers.io());
    }

}