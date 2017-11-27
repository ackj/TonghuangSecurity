package com.meilun.security.smart.discover.view;

import android.widget.ImageView;

import com.meilun.security.smart.R;
import com.meilun.security.smart.entity.bean.NewsBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/11/25 0025 18:30.
 * Email: liujia95me@126.com
 */

public class NewsListRVAdapter extends BaseRecyclerViewAdapter<NewsBean.DataBean, BaseViewHolder> {

    public NewsListRVAdapter(List<NewsBean.DataBean> data) {
        super(R.layout.item_item_news, data);
    }

    public NewsListRVAdapter() {
        super(R.layout.item_item_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {
        ImageView ivIcon = helper.getView(R.id.iv_icon);
        Glide.with(ivIcon.getContext())
                .load(item.getPhoto())
                .into(ivIcon);

        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_date, item.getTime());
    }
}
