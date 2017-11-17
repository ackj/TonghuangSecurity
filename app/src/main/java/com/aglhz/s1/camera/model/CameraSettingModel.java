package com.aglhz.s1.camera.model;

import android.text.TextUtils;

import com.aglhz.s1.camera.contract.CameraSettingContract;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/9/19 0019 08:51.
 * Email: liujia95me@126.com
 */

public class CameraSettingModel extends BaseModel implements CameraSettingContract.Model {
    @Override
    public Observable<BaseBean> requestModCamera(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (params.file != null) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), params.file);
            builder.addFormDataPart("file", params.file.getName(), requestBody);
        }
        builder.addFormDataPart("token", Params.token);
        builder.addFormDataPart("index", params.index + "");
        if (!TextUtils.isEmpty(params.deviceName)) {
            builder.addFormDataPart("name", params.deviceName);
        }
//        builder.addFormDataPart("roomFid", params.roomFid);
        builder.addFormDataPart("deviceType", params.deviceType);
        if (!TextUtils.isEmpty(params.devicePassword)) {
            builder.addFormDataPart("password", params.devicePassword);
        }

        return HttpHelper.getService(ApiService.class).requestModDevice(ApiService.requestModDevice
                , Params.token, builder.build())
                .subscribeOn(Schedulers.io());
    }
}
