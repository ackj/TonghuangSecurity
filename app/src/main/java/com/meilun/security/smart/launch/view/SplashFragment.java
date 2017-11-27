package com.meilun.security.smart.launch.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.UserHelper;
import com.meilun.security.smart.utils.CameraHelper;
import com.meilun.security.smart.R;
import com.meilun.security.smart.main.MainActivity;

import cn.itsite.abase.cache.SPCache;
import cn.itsite.abase.common.RxManager;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.network.http.HttpHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author：leguang on 2017/5/2 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class SplashFragment extends BaseFragment {
    public static final String TAG = SplashFragment.class.getSimpleName();
    private RxManager mRxManager = new RxManager();

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkLogin();
        CameraHelper.cameraLogin();
    }

    private void checkLogin() {
        mRxManager.add(HttpHelper.getService(ApiService.class)
                .requestCheckToken(ApiService.requestCheckToken, UserHelper.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getData().getStatus() == 1) {
                        UserHelper.clear();
                    }
                    go2Main();
                }, throwable -> {
                    ALog.e(throwable);
                    go2Main();
                })
        );
    }

    private void go2Main() {
        boolean isFirst = (boolean) SPCache.get(_mActivity, Constants.IS_FIRST_ENTER, true);
        if (isFirst) {
            _mActivity.start(WelcomeFragment.newInstance());
            return;
        }
        startActivity(new Intent(_mActivity, MainActivity.class));
        _mActivity.overridePendingTransition(0, 0);
        //此处之所以延迟退出是因为立即退出在小米手机上会有一个退出跳转动画，而我不想要这个垂直退出的跳转动画。
        new Handler().postDelayed(() -> _mActivity.finish(), 1000);
    }

    @Override
    public void onDestroyView() {
        mRxManager.clear();
        super.onDestroyView();
    }
}
