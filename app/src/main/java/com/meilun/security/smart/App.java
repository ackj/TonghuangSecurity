package com.meilun.security.smart;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.meilun.security.smart.common.ApiService;
import com.meilun.security.smart.common.BoxingGlideLoader;
import com.meilun.security.smart.common.UserHelper;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.register.HuaWeiRegister;
import com.alibaba.sdk.android.push.register.MiPushRegister;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.p2p.core.P2PSpecial.P2PSpecial;

import cn.itsite.abase.BaseApplication;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.network.http.HttpHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by leguang on 2017/6/22 0022.
 * Email：langmanleguang@qq.com
 */
public class App extends BaseApplication implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = App.class.getSimpleName();
    //    public final static String APPID="1e9a2c3ead108413e8218a639c540e44";
    public final static String APPID = "fe17b3e35717f4006a5d10a9106eb232";
    //    public final static String APPToken="7db7b2bff80a025a3dad546a4d5a6c3ee545568d4e0ce9609c0585c71c287d08";
    public final static String APPToken = "386294f10b00850ea94450cc335cbaefad3d615b68a73260e921b9c34bf39f3b";
    //前两位是客户APP唯一编号(00.00 由技威分配),后两位是APP版本号(客户自定义),此参数不可省略
    public final static String APPVersion = "05.72.00.00";
    public static String deviceID;

    @Override
    public void onCreate() {
        super.onCreate();
        initDate();
        initPush();//初始化推送。
        initBoxing();
        initP2P(this);
    }

    private void initDate() {
        UserHelper.init();
    }

    private void initBoxing() {
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
    }

    private void initP2P(App app) {
        P2PSpecial.getInstance().init(app, APPID, APPToken, APPVersion);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ALog.e("onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ALog.e("onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ALog.e("onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ALog.e("onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ALog.e("onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        ALog.e("onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ALog.e("onActivityDestroyed");
    }

    /**
     * 初始化云推送通道
     */
    public void initPush() {
        PushServiceFactory.init(mContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(mContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                ALog.e(TAG, "init cloudchannel success");
                ALog.e(TAG, "getDeviceId-->" + pushService.getDeviceId());
                deviceID = "and_" + pushService.getDeviceId();
                HttpHelper.getService(ApiService.class)
                        .registerDevice(ApiService.registerDevice, UserHelper.token, deviceID, UserHelper.account, "userType")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseBean -> ALog.e(TAG, baseBean.getOther().getMessage()));
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                ALog.e(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });

        // 注册方法会自动判断是否支持小米系统推送，如不支持会跳过注册。
        MiPushRegister.register(mContext, "2882303761517633954", "5551763357954");

        // 注册方法会自动判断是否支持华为系统推送，如不支持会跳过注册。
        HuaWeiRegister.register(mContext);
    }
}
