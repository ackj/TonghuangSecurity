package com.aglhz.s1.dialog;

import android.graphics.Color;

import com.aglhz.s1.R;
import com.aglhz.s1.database.ItemBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/6 0006 15:57.
 * Email: liujia95me@126.com
 */

public class SelectorItemRVAdapter extends BaseRecyclerViewAdapter<ItemBean, BaseViewHolder> {

    public SelectorItemRVAdapter(List<ItemBean> data) {
        super(R.layout.item_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemBean item) {
        helper.setTextColor(R.id.text1, item.isSelect ? Color.RED : 0xFF222222)
                .setText(R.id.text1, item.name)
                .addOnClickListener(R.id.text1)
        ;
    }
}
