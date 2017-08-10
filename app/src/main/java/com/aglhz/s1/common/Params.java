package com.aglhz.s1.common;


/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    private static final String TAG = Params.class.getSimpleName();
    public static String test_token = "tk_cdeac90e-5690-4163-9da4-1f276d293608";//测试用
    public static String token;
    public int page;
    public int pageSize = 20;
    public String sc = "AglhzSmart";
    public String user = "";
    public String pwd = "";
    public String pwd0 = "";
    public String pwd1 = "";
    public String pwd2 = "";
    public String account;
    public String verifyCode;
    public String verifyType;
    public String password1;
    public String password2;
    public String phoneNo = "";
    public String gateway = "";
    public String dstatus = "";
    public String no = "";
    public String name = "";

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        return params;
    }

    @Override
    public String toString() {
        return "Params{" +
                "sc='" + sc + '\'' +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwd0='" + pwd0 + '\'' +
                ", pwd1='" + pwd1 + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                '}';
    }
}