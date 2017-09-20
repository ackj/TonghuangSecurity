package com.aglhz.s1.camera;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.CameraBean;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/9/18 0018 09:42.
 * Email: liujia95me@126.com
 */

public class CameraListRVAdapter extends BaseRecyclerViewAdapter<CameraBean.DataBean, BaseViewHolder> {

    public CameraListRVAdapter() {
        super(R.layout.item_room_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, CameraBean.DataBean item) {
        helper.setImageResource(R.id.iv_icon_item_room_type, "添加监控".equals(item.getName()) ?
                R.drawable.ic_add_security_140px : R.drawable.bg_shexiangtou_180px)
                .setText(R.id.tv_name_item_room_type,item.getName());
    }
}
