package com.meilun.security.smart.linkage.contract;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DeviceListBean;
import com.meilun.security.smart.entity.bean.SceneBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

/**
 * Author: LiuJia on 2017/8/28 0028 10:28.
 * Email: liujia95me@126.com
 */

public interface AddLinkageContract  {

    interface View extends BaseContract.View {
        void responseAddSuccess(BaseBean bean);
        void responseSceneList(SceneBean bean);
        void responseDeviceList(List<DeviceListBean.DataBean.SubDevicesBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestNewLinkage(Params params);
        void requestSceneList(Params params);
        void requestDeviceList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestNewLinkage(Params param);
        Observable<SceneBean> requestSceneList(Params params);
        Observable<DeviceListBean> requestDeviceList(Params params);
    }
}
