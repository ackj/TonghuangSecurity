package com.aglhz.s1;

import cn.itsite.abase.BaseApplication;
import cn.itsite.abase.exception.AppExceptionHandler;
import cn.itsite.abase.log.ALog;

/**
 * Author： Administrator on 2017/5/2 0002.
 * Email： liujia95me@126.com
 */
public class App extends BaseApplication {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {

            ALog.init(true, "S1");
        } else {
            ALog.init(false, "S1");//在release版中禁止打印log。
            Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler.getInstance(this));//在release版中处理全局异常。
        }
    }


}
