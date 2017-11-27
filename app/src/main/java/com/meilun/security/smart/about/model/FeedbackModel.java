package com.meilun.security.smart.about.model;


import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.about.contract.FeedbackContract;
import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class FeedbackModel extends BaseModel implements FeedbackContract.Model {

    private final String TAG = FeedbackModel.class.getSimpleName();

    @Override
    public Observable<BaseBean> requestSubmitFeedback(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestFeedback(ApiService.requestFeedback,
                        params.token, params.des, params.contact)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void start(Object request) {

    }
}