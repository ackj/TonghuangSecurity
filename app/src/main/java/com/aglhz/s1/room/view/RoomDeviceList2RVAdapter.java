package com.aglhz.s1.room.view;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/8/20 0020 20:00.
 * Email: liujia95me@126.com
 */

public class RoomDeviceList2RVAdapter extends BaseRecyclerViewAdapter<DeviceListBean.DataBean.SubDevicesBean, BaseViewHolder> {

    public RoomDeviceList2RVAdapter() {
        super(R.layout.item_device);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceListBean.DataBean.SubDevicesBean item) {
        helper.setText(R.id.tv_device_name, item.getName())
                .addOnClickListener(R.id.iv_setting);
    }
}
