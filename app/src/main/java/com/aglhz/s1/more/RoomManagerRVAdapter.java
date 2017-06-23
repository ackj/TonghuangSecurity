package com.aglhz.s1.more;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.RoomBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/31 0031 11:12.
 * Email: liujia95me@126.com
 */

public class RoomManagerRVAdapter extends BaseRecyclerViewAdapter<RoomBean, BaseViewHolder> {

    public RoomManagerRVAdapter(List<RoomBean> data) {
        super(R.layout.item_room_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomBean item) {
        helper.setImageResource(R.id.iv_icon_item_room_type, item.icon)
                .setText(R.id.tv_name_item_room_type, item.name);
    }
}
