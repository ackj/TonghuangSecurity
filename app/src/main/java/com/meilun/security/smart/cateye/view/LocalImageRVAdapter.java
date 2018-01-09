package com.meilun.security.smart.cateye.view;

import com.chad.library.adapter.base.BaseViewHolder;
import com.meilun.security.smart.R;
import com.meilun.security.smart.entity.bean.MediaListItem;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/4 0004 20:55.
 * Email: liujia95me@126.com
 */

public class LocalImageRVAdapter extends BaseRecyclerViewAdapter<MediaListItem, BaseViewHolder> {

    public LocalImageRVAdapter(List<MediaListItem> data) {
        super(R.layout.item_cateye_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MediaListItem item) {
        helper.setText(R.id.tv_create_time, item.description)
                .setText(R.id.tv_file_path, item.fileName)
                .setVisible(R.id.tv_file_path,true)
                .setText(R.id.tv_no, (helper.getLayoutPosition() + 1)+"")
                .setImageBitmap(R.id.tv_picture, item.bmp);
    }
}
