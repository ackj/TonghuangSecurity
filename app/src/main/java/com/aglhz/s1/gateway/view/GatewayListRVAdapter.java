package com.aglhz.s1.gateway.view;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

public class GatewayListRVAdapter extends BaseRecyclerViewAdapter<GatewaysBean.DataBean, BaseViewHolder> {

    public GatewayListRVAdapter() {
        super(R.layout.item_gateway);
    }

    @Override
    protected void convert(BaseViewHolder helper, GatewaysBean.DataBean item) {
        helper.setText(R.id.tv_people_type, item.getIsManager() == 1 ? "管理员" : "成员")
                .setText(R.id.tv_name, item.getResidence().getAddr());
    }
}