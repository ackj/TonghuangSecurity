package com.aglhz.s1.room.model;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.room.contract.AddDeviceContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.schedulers.Schedulers;

public class AddDeviceModel extends BaseModel implements AddDeviceContract.Model {
    @Override
    public void start(Object request) {

    }

	@Override
    public Observable<BaseBean> requestnewDevice(Params params) {
        return HttpHelper.getService(ApiService.class).requestNewDevice(ApiService.requestNewDevice
                , params.token,params.deviceType,params.name,params.roomFid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestModDevice(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if(params.file!=null){
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), params.file);
            builder.addFormDataPart("file", params.file.getName(), requestBody);
        }
        builder.addFormDataPart("index", params.index+"");
        builder.addFormDataPart("name", params.name);
        builder.addFormDataPart("roomFid", params.roomFid);

        return HttpHelper.getService(ApiService.class).requestModDevice(ApiService.requestModDevice
                , params.token,builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDelDevice(Params params) {
        return HttpHelper.getService(ApiService.class).requestDelDevice(ApiService.requestDelDevice
                , params.token,params.index)
                .subscribeOn(Schedulers.io());
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

}