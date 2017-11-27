package com.meilun.security.smart.room.model;

import android.text.TextUtils;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.RoomsBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.room.contract.AddDeviceContract;

import cn.itsite.abase.log.ALog;
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
                , params.token, params.deviceType, params.name, params.roomFid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestModDevice(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (params.file != null) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), params.file);
            builder.addFormDataPart("file", params.file.getName(), requestBody);
        }
        builder.addFormDataPart("index", params.index + "");
        builder.addFormDataPart("name", params.name);
        builder.addFormDataPart("deviceType", params.deviceType);
        ALog.e(TAG,"roomFid:"+params.roomFid);
        if(!TextUtils.isEmpty(params.roomFid)){
            builder.addFormDataPart("roomFid", params.roomFid);
        }

        return HttpHelper.getService(ApiService.class).requestModDevice(ApiService.requestModDevice
                , params.token, builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDelDevice(Params params) {
        return HttpHelper.getService(ApiService.class).requestDelDevice(ApiService.requestDelDevice
                , params.token, params.index)
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