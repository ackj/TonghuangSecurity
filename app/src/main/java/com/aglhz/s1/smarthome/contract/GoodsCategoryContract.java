package com.aglhz.s1.smarthome.contract;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.FirstLevelBean;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import rx.Observable;


/**
 * Author: LiuJia on 2017/5/22 0022 10:22.
 * Email: liujia95me@126.com
 */

public interface GoodsCategoryContract {

    interface View extends BaseContract.View {
        void responseFirstLevel(List<FirstLevelBean.DataBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestFirstLevel(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<FirstLevelBean> requestFirstLevel(Params params);
    }

}
