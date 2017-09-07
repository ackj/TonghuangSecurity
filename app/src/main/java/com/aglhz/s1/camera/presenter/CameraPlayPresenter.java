package com.aglhz.s1.camera.presenter;

import com.aglhz.s1.camera.contract.CameraPlayContract;
import com.macrovideo.sdk.custom.DeviceInfo;
import com.macrovideo.sdk.media.LoginHandle;
import com.macrovideo.sdk.media.LoginHelper;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.presenter.base.BasePresenter;

/**
 * Author: LiuJia on 2017/9/6 0006 15:00.
 * Email: liujia95me@126.com
 */

public class CameraPlayPresenter extends BasePresenter implements CameraPlayContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public CameraPlayPresenter(BaseContract.View mView) {
        super(mView);
    }

    @Override
    public void requestLogin(DeviceInfo info) {
        LoginHandle deviceParam = LoginHelper.getDeviceParam(info);
        ((CameraPlayContract.View) getView()).responseLoginSuccess(deviceParam);

    }
}
