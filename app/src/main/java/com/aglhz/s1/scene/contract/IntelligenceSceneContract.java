package com.aglhz.s1.scene.contract;

import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface IntelligenceSceneContract { 

    interface View extends BaseContract.View {
        void responseSceneList(BaseBean bean);
        void responseStartScene(BaseBean bean);
        void responseDeleteScene(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestSceneList(Params params);
        void requestStartScene(Params params);
        void requestDeleteScene(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestSceneList(Params params);
        Observable<BaseBean> requestStartScene(Params params);
        Observable<BaseBean> requestDeleteScene(Params params);
    }
}