package com.meilun.security.smart.entity.bean;

import com.meilun.security.smart.App;

import cn.itsite.abase.utils.DensityUtils;

/**
 * Author: LiuJia on 2017/5/17 0017 20:58.
 * Email: liujia95me@126.com
 */

public class DeviceButtonBean {

    public int x;
    public int y;

    private DeviceButtonBean() {
    }

    public DeviceButtonBean(float percentX, float percentY) {
        x = (int) (DensityUtils.getDisplayWidth(App.mContext) * percentX);
        int displayHeight = DensityUtils.getDisplayHeight(App.mContext)-DensityUtils.dp2px(App.mContext,56);
        y = (int) (displayHeight * percentY);
    }
}
