package com.aglhz.s1;

import android.app.Application;
import android.content.Context;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Author： Administrator on 2017/5/2 0002.
 * Email： liujia95me@126.com
 */
public class BaseApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        Fragmentation.builder().stackViewMode(Fragmentation.BUBBLE).install();
    }
}
