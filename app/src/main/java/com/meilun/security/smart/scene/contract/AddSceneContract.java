package com.meilun.security.smart.scene.contract;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface AddSceneContract {

    interface View extends BaseContract.View {

        void responseAddScene(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {

        void requestAddScene(Params params);
    }

    interface Model extends BaseContract.Model {

        Observable<BaseBean> requestAddScene(Params params);
    }
}