package com.meilun.security.smart.entity.bean;

import com.Player.web.response.DevItemInfo;

import java.io.Serializable;

/**
 * Author: LiuJia on 2018/1/3 0003 11:00.
 * Email: liujia95me@126.com
 */

public class MainDeviceBean implements Serializable{

    public String name;
    public String deviceId;
    public String userName;//猫眼播放需要的userName
    public String userPwd;//猫眼播放需要的userPwd
    public DevItemInfo cateyeBean;

}
