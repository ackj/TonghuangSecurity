package com.aglhz.s1.history;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.HistoryBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/4/27 0027 16:16.
 * Email: liujia95me@126.com
 */

public class HistoryRVAdapter extends BaseRecyclerViewAdapter<HistoryBean, BaseViewHolder> {

    public HistoryRVAdapter(List<HistoryBean> data) {
        super(R.layout.item_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryBean item) {
        helper.setText(R.id.tv_id, item.id)
                .setText(R.id.tv_content, item.content)
                .setText(R.id.tv_time, item.time);
    }
}
