package com.aglhz.s1.discover.view;

import android.widget.ImageView;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.DiscoverBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/11/22 0022 17:33.
 * Email: liujia95me@126.com
 */

public class NewsRVAdapter extends BaseRecyclerViewAdapter<DiscoverBean.DataBean.NewsBean, BaseViewHolder> {

    public NewsRVAdapter(List<DiscoverBean.DataBean.NewsBean> data) {
        super(R.layout.item_item_news, data);
    }

    public NewsRVAdapter() {
        super(R.layout.item_item_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverBean.DataBean.NewsBean item) {
        ImageView ivIcon = helper.getView(R.id.iv_icon);
        Glide.with(ivIcon.getContext())
                .load(item.getPhoto())
                .into(ivIcon);

        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_date, item.getTime());
    }
}
