package com.meilun.security.smart.linkage.contract;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.LinkageBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

public interface LinkageListContract {

    interface View extends BaseContract.View {
        void responseLinkageList(List<LinkageBean.DataBean> bean);
        void responseModLinkage(BaseBean bean);
        void responseDeleteLinkage(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestLinkageList(Params params);
        void requestModLinkage(Params params);
        void requestDeleteLinkage(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<LinkageBean> requestLinkageList(Params params);
        Observable<BaseBean> requestModLinkage(Params params);
        Observable<BaseBean> requestDeleteLinkage(Params params);
    }
}