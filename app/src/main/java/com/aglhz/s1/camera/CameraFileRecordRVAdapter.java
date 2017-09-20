package com.aglhz.s1.camera;

import com.aglhz.s1.R;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/9/19 0019 10:52.
 * Email: liujia95me@126.com
 */

public class CameraFileRecordRVAdapter extends BaseRecyclerViewAdapter<String,BaseViewHolder>{

    public CameraFileRecordRVAdapter() {
        super(R.layout.item_file_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, String str) {
        helper.setText(R.id.tv_info,str);
    }
}
