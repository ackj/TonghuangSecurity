package com.aglhz.s1.common;

import com.aglhz.s1.BuildConfig;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class Constants {
    public static final String TAG = Constants.class.getSimpleName();
    public static final int RESPONSE_CODE_NOMAL = 200;

    //不允许new
    private Constants() {
        throw new Error("Do not need instantiate!");
    }

    public static final String PRESS_AGAIN = "再按一次退出";

    public static final String IS_FIRST_ENTER = "is_first_enter";


    //配网模块
    public static final String WIFI_NAME = "wifi_name";
    public static final String WIFI_PASSWORD = "WIFI_PASSWORD";

    //——————————————以下是区分debug版和非debug版的baseurl——————————————————————

    public static String BASE_USER = "";

    static {
        if (BuildConfig.DEBUG) {
            //调试可以改这里的地址。
            BASE_USER = "http://www.aglhz.com:8076/memberSYS-m";           //用户
        } else {
            //这里的是正式版的基础地址，永远不要动。
            BASE_USER = "http://www.aglhz.com:8076/memberSYS-m";           //用户
        }
    }
    //——————————————以上是区分debug版和非debug版的baseurl——————————————————————

}
