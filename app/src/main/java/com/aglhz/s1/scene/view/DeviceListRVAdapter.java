package com.aglhz.s1.scene.view;

import android.widget.ImageView;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/8/12 0012 16:56.
 * Email: liujia95me@126.com
 */

public class DeviceListRVAdapter extends BaseRecyclerViewAdapter<DeviceListBean.DataBean.SubDevicesBean, BaseViewHolder> {

    public DeviceListRVAdapter() {
        super(R.layout.item_rv_device_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceListBean.DataBean.SubDevicesBean item) {
        helper.setText(R.id.tv_name_item_rv_device_list_fragment, item.getName())
                .setText(R.id.tv_online_item_rv_device_list_fragment, item.getIsOline() == 1 ? "在线" : "离线");

        Glide.with(App.mContext)
                .load(item.getIcon())
                .into((ImageView) helper.getView(R.id.iv_icon_item_rv_device_list_fragment));
    }
}
