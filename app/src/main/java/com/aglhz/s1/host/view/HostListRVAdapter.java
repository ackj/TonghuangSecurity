package com.aglhz.s1.host.view;

import android.graphics.Color;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

public class HostListRVAdapter extends BaseRecyclerViewAdapter<GatewaysBean.DataBean, BaseViewHolder> {

    public HostListRVAdapter() {
        super(R.layout.item_rv_gateway);
    }

    @Override
    protected void convert(BaseViewHolder helper, GatewaysBean.DataBean item) {
        helper.setText(R.id.tv_people_type_item_rv_gateway, item.getIsManager() == 1 ? "管理员" : "成员")
                .setText(R.id.tv_current_item_rv_gateway, item.getIsCurrent() == 1 ? "当前主机" : "")
                .setText(R.id.tv_name_item_rv_gateway, item.getName() + (item.getIsOnline() == 1 ? "　(在线)" : "　(离线)"))
                .setTextColor(R.id.tv_name_item_rv_gateway,
                        item.getIsOnline() == 1 ? Color.parseColor("#999999") : Color.parseColor("#FF0000"));
    }
}