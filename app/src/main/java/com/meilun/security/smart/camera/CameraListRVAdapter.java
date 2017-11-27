package com.meilun.security.smart.camera;

import com.meilun.security.smart.R;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/11/22 0022 18:18.
 * Email: liujia95me@126.com
 */

public class CameraListRVAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder> {

    public CameraListRVAdapter() {
        super(R.layout.item_camera);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
