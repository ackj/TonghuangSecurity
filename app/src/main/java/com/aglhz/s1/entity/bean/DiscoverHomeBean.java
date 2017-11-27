package com.aglhz.s1.entity.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Author: LiuJia on 2017/11/25 0025 17:52.
 * Email: liujia95me@126.com
 */

public class DiscoverHomeBean implements MultiItemEntity {

    public static final int TYPE_BANNER = 0;
    public static final int TYPE_NOTICE = 1;
    public static final int TYPE_BUTTONS = 2;
    public static final int TYPE_NEWS = 3;
    public static final int TYPE_STORE = 4;

    public List<DiscoverBean.DataBean.AdvsBean> bannerss;
    public List<DiscoverBean.DataBean.NewsBean> news;
    public List<String> notices;
    public List<SubCategoryBean.DataBean> stores;

    public int type;

    @Override
    public int getItemType() {
        return type;
    }
}
