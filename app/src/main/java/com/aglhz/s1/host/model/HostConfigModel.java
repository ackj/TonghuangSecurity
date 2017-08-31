package com.aglhz.s1.host.model;


import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.host.contract.HostConfigContract;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责设置主机模块的Model层内容。
 */

public class HostConfigModel extends BaseModel implements HostConfigContract.Model {
    private final String TAG = HostConfigModel.class.getSimpleName();

    @Override
    public Observable<BaseBean> requestHostConfig(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestHostConfig(ApiService.requestHostConfig,
                        params.gateway,
                        params.token,
                        params.type,
                        params.subType,
                        params.val)
                .subscribeOn(Schedulers.io());
    }
}