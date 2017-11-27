package com.aglhz.s1.smarthome.view;

import android.widget.ImageView;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.FirstLevelBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/22 0022 10:49.
 * Email: liujia95me@126.com
 */

public class GoodsCategoryRVAdapter extends BaseRecyclerViewAdapter<FirstLevelBean.DataBean, BaseViewHolder> {

    public GoodsCategoryRVAdapter() {
        super(R.layout.item_goods_gategory);
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstLevelBean.DataBean item) {
        ImageView image = helper.getView(R.id.iv_goods_gategory);

        Glide.with(App.mContext)
                .load(item.getImage())
                .into(image);
    }
}
