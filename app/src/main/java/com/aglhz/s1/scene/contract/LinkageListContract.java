package com.aglhz.s1.scene.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.LinkageBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;
public interface LinkageListContract {

    interface View extends BaseContract.View {
        void responseLinkageList(List<LinkageBean.DataBean> bean);
        void responseLinkageSwitch(BaseBean bean);
        void responseDeleteLinkage(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestLinkageList(Params params);
        void requestLinkageSwitch(Params params);
        void requestDeleteLinkage(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<LinkageBean> requestLinkageList(Params params);
        Observable<BaseBean> requestLinkageSwitch(Params params);
        Observable<BaseBean> requestDeleteLinkage(Params params);
    }
}