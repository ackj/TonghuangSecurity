package com.aglhz.s1.adapter;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.SecurityBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/4/27 0027 08:49.
 * Email: liujia95me@126.com
 */

public class SecurityAdapter extends BaseRecyclerViewAdapter<SecurityBean, BaseViewHolder> {

    public SecurityAdapter(List<SecurityBean> data) {
        super(R.layout.item_security, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecurityBean item) {
        helper.setText(R.id.tv_name, item.name)
                .setImageResource(R.id.iv_icon, item.icon);

    }
}
