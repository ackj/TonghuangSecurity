package com.aglhz.s1.more.view;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.RoomsBean;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/31 0031 11:12.
 * Email: liujia95me@126.com
 */

public class RoomManagerRVAdapter extends BaseRecyclerViewAdapter<RoomsBean.DataBean.RoomListBean, BaseViewHolder> {

    public RoomManagerRVAdapter() {
        super(R.layout.item_room_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomsBean.DataBean.RoomListBean item) {
        helper.setImageResource(R.id.iv_icon_item_room_type, item.getName().equals("添加房间")?R.drawable.ic_add_security_140px:R.mipmap.ic_launcher)
                .setText(R.id.tv_name_item_room_type, item.getName());
    }
}
