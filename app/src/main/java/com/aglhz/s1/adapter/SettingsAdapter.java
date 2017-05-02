package com.aglhz.s1.adapter;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.SettingsBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/4/27 0027 17:30.
 * Email: liujia95me@126.com
 */

public class SettingsAdapter extends BaseRecyclerViewAdapter<SettingsBean, BaseViewHolder> {


    public SettingsAdapter(List<SettingsBean> data) {
        super(R.layout.item_settings, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingsBean item) {
        helper.setText(R.id.tv_title, item.title)
                .setText(R.id.tv_value, item.data)
                .setImageResource(R.id.iv_icon, item.icon);
    }
}
