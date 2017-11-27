package com.meilun.security.smart.security.model;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.security.contract.AddDetectorContract;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DevicesBean;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import okhttp3.MultipartBody;
import rx.Observable;
import rx.schedulers.Schedulers;

public class AddDetectorModel extends BaseModel implements AddDetectorContract.Model {
    private static final String TAG = AddDetectorModel.class.getSimpleName();

    @Override
    public Observable<DevicesBean> requestDetectorList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSensorTypeList(ApiService.requestSensorTypeList,
                        params.token,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestAddDetector(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("sensorType", params.sensorType);
        builder.addFormDataPart("name", params.name);
        return HttpHelper.getService(ApiService.class)
                .reqeuestNewsensor(ApiService.reqeuestNewsensor,
                        builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> reqeuestCancellationOfSensorLearning(Params params) {
        return HttpHelper.getService(ApiService.class)
                .reqeuestCancellationOfSensorLearning(ApiService.reqeuestCancellationOfSensorLearning,
                        params.token)
                .subscribeOn(Schedulers.io());
    }
}