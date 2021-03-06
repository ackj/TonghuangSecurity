package com.meilun.security.smart.discover.contract;

import com.meilun.security.smart.entity.bean.BaseBean;
import com.meilun.security.smart.entity.bean.DiscoverBean;
import com.meilun.security.smart.entity.bean.SubCategoryBean;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DeviceLogBean;
import com.meilun.security.smart.entity.bean.FirstLevelBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;

/**
 * Author: LiuJia on 2017/11/23 0023 10:44.
 * Email: liujia95me@126.com
 */

public interface DiscoverContract {
    interface View extends BaseContract.View {

        //智慧商城一级列表
        void responseFirstLevel(List<FirstLevelBean.DataBean> datas);

        //智慧商城二级列表
        void responseSubCategoryList(List<SubCategoryBean.DataBean> datas);

        void responseDeviceLogs(List<DeviceLogBean.DataBean.LogsBean> data);

        void responseDiscoverPage(DiscoverBean bean);

        void responseSwichState(BaseBean baseBean);

    }

    interface Presenter extends BaseContract.Presenter {

        //智慧商城一级列表
        void requestFirstLevel(Params params);

        //智慧商城二级列表
        void requestSubCategoryList(Params params);

        void requestDeviceLogs(Params params);

        void requestDiscoverPage(Params params);

        void requestSwichState(Params params);

    }

    interface Model extends BaseContract.Model {

        //智慧商城一级列表
        Observable<FirstLevelBean> requestFirstLevel(Params params);

        //智慧商城二级列表
        Observable<SubCategoryBean> requestSubCategoryList(Params params);

        Observable<DeviceLogBean> requestDeviceLogs(Params params);

        Observable<DiscoverBean> requestDiscoverPage(Params params);

        Observable<BaseBean> requestSwichState(Params params);
    }
}
