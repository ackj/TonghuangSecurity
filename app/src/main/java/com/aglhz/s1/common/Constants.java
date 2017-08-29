package com.aglhz.s1.common;

import com.aglhz.s1.BuildConfig;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class Constants {
    public static final String TAG = Constants.class.getSimpleName();
    public static final int RESPONSE_CODE_SUCCESS = 200;

    //不允许new
    private Constants() {
        throw new Error("Do not need instantiate!");
    }

    //--------------------以下是区分debug版和非debug版的baseurl-----------

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
    //-------------------以上是区分debug版和非debug版的baseurl-----------------

    public static final String PRESS_AGAIN = "再按一次退出";

    public static final String IS_FIRST_ENTER = "is_first_enter";

    //配网模块
    public static final String WIFI_NAME = "wifi_name";
    public static final String WIFI_PASSWORD = "WIFI_PASSWORD";

    //安全模块
    public static final String GATEWAY_STATE_CANCLE = "cancel";
    public static final String GATEWAY_STATE_HOME = "home";
    public static final String GATEWAY_STATE_FARAWAY = "faraway";

    //key
    public static final String KEY_HOST_NUMBER = "host_number";


    //额外推送类型。
    public static final String SENSOR_LEARN = "sensor_learn";//传感器学习
    public static final String GW_ALARM_GAS = "gw_alarm_gas";//燃气报警
    public static final String GW_NOTIFIY_DEFENSE_ST = "gw_defense_satus";//主机布防状态变更

    public static final int PAGE_SIZE = 20;

    //设备分类：sensor/device_ctrl 控制类或者探测器类。默认控制类
    public static final String DEVICE_CTRL = "device_ctrl";
    public static final String SENSOR = "sensor";
    public static final String TIME = "time";
    public static final String SCENE = "scene";
    public static final String DEVICE = "device";

    public static final String KEY_SELECTOR = "key_SELECTOR";

}
