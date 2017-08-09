package com.aglhz.s1.common;


/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    private static final String TAG = Params.class.getSimpleName();
    public static String token = "tk_cdeac90e-5690-4163-9da4-1f276d293608";//测试用
    public String sc = "AglhzYsq";

    public static Params getInstance(){
        Params params = new Params();
        return params;
    }

    @Override
    public String toString() {
        return "Params{" +
                "sc='" + sc + '\'' +
                '}';
    }
}