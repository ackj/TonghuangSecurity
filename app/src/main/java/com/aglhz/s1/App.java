package com.aglhz.s1;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.BoxingGlideLoader;
import com.aglhz.s1.common.UserHelper;
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
    public final static String APPID = "831c12862fe220d2b840623bf8187981";
    //    public final static String APPToken="7db7b2bff80a025a3dad546a4d5a6c3ee545568d4e0ce9609c0585c71c287d08";
    public final static String APPToken = "b5a886e7faa04150afa872291cc5cda211c603c302bd8edaea6ca2953422354d";
    //前两位是客户APP唯一编号(00.00 由技威分配),后两位是APP版本号(客户自定义),此参数不可省略
    public final static String APPVersion = "05.32.00.00";
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
