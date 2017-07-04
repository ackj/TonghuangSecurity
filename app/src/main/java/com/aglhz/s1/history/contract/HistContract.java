package com.aglhz.s1.history.contract;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.common.Params;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface HistContract { 

    interface View extends BaseContract.View {
        void responseHistory(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestHistory(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestHistory(Params params);
    }
}