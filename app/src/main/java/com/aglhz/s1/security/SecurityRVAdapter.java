package com.aglhz.s1.security;

import android.widget.ImageView;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.DevicesBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.BaseApplication;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/4/27 0027 08:49.
 * Email: liujia95me@126.com
 */
public class SecurityRVAdapter extends BaseRecyclerViewAdapter<DevicesBean.DataBean.DeviceTypeListBean, BaseViewHolder> {

    public SecurityRVAdapter() {
        super(R.layout.item_security);
    }

    @Override
    protected void convert(BaseViewHolder helper, DevicesBean.DataBean.DeviceTypeListBean item) {
        helper.setText(R.id.tv_name_item_security, item.getName());
        ImageView ivSecurity = helper.getView(R.id.iv_icon_item_security);
        Glide.with(BaseApplication.mContext)
                .load(item.getIcon().equals("add_icon") ? R.drawable.ic_add_security_140px : item.getIcon())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivSecurity);
    }
}
