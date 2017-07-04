package com.aglhz.s1.security.contract;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/7/4 0004 15:04.
 * Email: liujia95me@126.com
 */

public interface DetectorPropertyContract {

    interface View extends BaseContract.View {
        void responseDetectorProperty(BaseBean baseBean);
//        void responseUpdateDetectorName(BaseBean baseBean);
//        void responseUpdateDetectorPicture(BaseBean baseBean);
//        void responseUpdateDetectorLine(BaseBean baseBean);
//        void responseUpdateDetectorIsDelay(BaseBean baseBean);
//        void responseDeleteDetector(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestDetectorProperty(Params params);
//        void requestUpdateDetectorName(BaseBean baseBean);
//        void requestUpdateDetectorPicture(BaseBean baseBean);
//        void requestUpdateDetectorLine(BaseBean baseBean);
//        void requestUpdateDetectorIsDelay(BaseBean baseBean);
//        void requestDeleteDetector(BaseBean baseBean);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestDetectorProperty(Params params);
//        Observable<BaseBean> requestUpdateDetectorName(Params params);
//        Observable<BaseBean> requestUpdateDetectorPicture(BaseBean baseBean);
//        Observable<BaseBean> requestUpdateDetectorLine(BaseBean baseBean);
//        Observable<BaseBean> requestUpdateDetectorIsDelay(BaseBean baseBean);
//        Observable<BaseBean> requestDeleteDetector(BaseBean baseBean);
    }
}
