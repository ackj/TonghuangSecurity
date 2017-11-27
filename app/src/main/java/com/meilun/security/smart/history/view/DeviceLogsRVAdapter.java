package com.meilun.security.smart.history.view;

import com.meilun.security.smart.R;
import com.meilun.security.smart.entity.bean.DeviceLogBean;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/4/27 0027 16:16.
 * Email: liujia95me@126.com
 */

public class DeviceLogsRVAdapter extends BaseRecyclerViewAdapter<DeviceLogBean.DataBean.LogsBean, BaseViewHolder> {

    public DeviceLogsRVAdapter() {
        super(R.layout.item_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceLogBean.DataBean.LogsBean item) {
        helper.setText(R.id.tv_host_item_history, item.getGatewayName())
                .setText(R.id.tv_title_item_history, item.getTitle())
                .setText(R.id.tv_content_item_history, item.getDes())
                .setText(R.id.tv_time_item_history, item.getLogtime());
    }
}
