package com.aglhz.s1.camera.presenter;

import com.aglhz.s1.camera.contract.CameraPlayContract;
import com.macrovideo.sdk.custom.DeviceInfo;
import com.macrovideo.sdk.defines.ResultCode;
import com.macrovideo.sdk.media.LoginHandle;
import com.macrovideo.sdk.media.LoginHelper;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        Observable.create(new ObservableOnSubscribe<LoginHandle>() {
            @Override
            public void subscribe(ObservableEmitter<LoginHandle> e) throws Exception {
                LoginHandle deviceParam = LoginHelper.getDeviceParam(info);
                if (deviceParam == null) {
                    e.onError(new Throwable("登录失败，服务器不在线"));
                    return;
                }
                if (deviceParam.getnResult() == ResultCode.RESULT_CODE_SUCCESS) {
                    e.onNext(deviceParam);
                } else {
                    e.onError(new Throwable("登录失败，结果码：" + deviceParam.getnResult()));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    ((CameraPlayContract.View) getView()).responseLoginSuccess(o);
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ((CameraPlayContract.View) getView()).error(throwable.getMessage());
                    }
                });

    }
}
