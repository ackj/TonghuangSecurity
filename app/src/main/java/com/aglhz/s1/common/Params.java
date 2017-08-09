package com.aglhz.s1.common;


/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    private static final String TAG = Params.class.getSimpleName();
    public static String token;
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

    public static Params getInstance() {
        Params params = new Params();
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