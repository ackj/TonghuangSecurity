package com.aglhz.s1.security.contract;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.bean.HostListBean;
import com.aglhz.s1.bean.SecurityBean;
import com.aglhz.s1.common.Params;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/7/4 0004 09:17.
 * Email: liujia95me@126.com
 */

public interface SecurityContract {

    interface View extends BaseContract.View {
        void responseSecurity(List<SecurityBean> listRecord);
        void responseHostList(HostListBean hostListBean);
        void responseChangedHostStatusSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestSecurity(Params params);
        void requestHostList(Params params);
        void responseChangedHostStatus(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<SecurityBean> requestSecurity(Params params);
        Observable<HostListBean> requestHostList(Params params);
        Observable<BaseBean> responseChangedHostStatus(Params params);
    }

}
