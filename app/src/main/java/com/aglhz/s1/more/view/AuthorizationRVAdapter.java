package com.aglhz.s1.more.view;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.AuthorizationBean;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/8/30 0030 11:18.
 * Email: liujia95me@126.com
 */

public class AuthorizationRVAdapter extends BaseRecyclerViewAdapter<AuthorizationBean.DataBean,BaseViewHolder> {

    public AuthorizationRVAdapter() {
        super(R.layout.item_authorization);
    }

    @Override
    protected void convert(BaseViewHolder helper, AuthorizationBean.DataBean item) {
        helper.setText(R.id.tv_phone,item.getMobile())
                .addOnClickListener(R.id.tv_unbound);
    }
}
