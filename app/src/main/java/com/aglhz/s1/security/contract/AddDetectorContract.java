package com.aglhz.s1.security.contract;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface AddDetectorContract { 

    interface View extends BaseContract.View {
        void responseDetectorList(BaseBean bean);
        void responseAddDetector(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestDetectorList(Params params);
        void requestAddDetector(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestDetectorList(Params params);
        Observable<BaseBean> requestAddDetector(Params params);
    }
}