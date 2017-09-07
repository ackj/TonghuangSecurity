package com.aglhz.s1.camera.contract;

import com.macrovideo.sdk.custom.DeviceInfo;
import com.macrovideo.sdk.media.LoginHandle;

import cn.itsite.abase.mvp.contract.base.BaseContract;

/**
 * Author: LiuJia on 2017/9/6 0006 15:01.
 * Email: liujia95me@126.com
 */

public interface CameraPlayContract {

    interface View extends BaseContract.View {
        void responseLoginSuccess(LoginHandle handle);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestLogin(DeviceInfo info);
    }
}
