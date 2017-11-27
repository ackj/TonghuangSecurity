package com.meilun.security.smart.security.contract;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DevicesBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface AddDetectorContract {

    interface View extends BaseContract.View {
        void responseDetectorList(List<DevicesBean.DataBean.DeviceTypeListBean> bean);

        void responseAddDetector(BaseBean baseBean);

        void responseCancellationOfSensorLearning(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestDetectorList(Params params);

        void requestAddDetector(Params params);

        void reqeuestCancellationOfSensorLearning(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<DevicesBean> requestDetectorList(Params params);

        Observable<BaseBean> requestAddDetector(Params params);

        Observable<BaseBean> reqeuestCancellationOfSensorLearning(Params params);
    }
}