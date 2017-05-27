package com.aglhz.s1.bean;

import com.aglhz.s1.BaseApplication;

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
        x = (int) (DensityUtils.getDisplayWidth(BaseApplication.mContext) * percentX);
        int displayHeight = DensityUtils.getDisplayHeight(BaseApplication.mContext)-DensityUtils.dp2px(BaseApplication.mContext,56);
        y = (int) (displayHeight * percentY);
    }
}
