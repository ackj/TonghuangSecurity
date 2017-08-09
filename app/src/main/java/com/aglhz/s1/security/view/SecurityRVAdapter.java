package com.aglhz.s1.security.view;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.SecurityBean1;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/4/27 0027 08:49.
 * Email: liujia95me@126.com
 */
public class SecurityRVAdapter extends BaseRecyclerViewAdapter<SecurityBean1, BaseViewHolder> {

    public SecurityRVAdapter(List<SecurityBean1> data) {
        super(R.layout.item_security, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecurityBean1 item) {
        helper.setText(R.id.tv_name_item_security, item.name)
                .setImageResource(R.id.iv_icon_item_security, item.icon);
    }
}
