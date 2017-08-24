package com.aglhz.s1.scene.view;

import com.aglhz.s1.R;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/8/12 0012 17:05.
 * Email: liujia95me@126.com
 */

public class DeviceMovementRVAdapter extends BaseRecyclerViewAdapter<String,BaseViewHolder> {

    public DeviceMovementRVAdapter() {
        super(R.layout.item_add_movement);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
