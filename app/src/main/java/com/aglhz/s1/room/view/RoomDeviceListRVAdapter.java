package com.aglhz.s1.room.view;

import android.view.View;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.RoomDeviceBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Author： Administrator on 2017/8/18 0018.
 * Email： liujia95me@126.com
 */
public class RoomDeviceListRVAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    public static final int TYPE_DEVICE = 100;
    public static final int TYPE_ON_OFF = 101;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RoomDeviceListRVAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_DEVICE, R.layout.item_device);
        addItemType(TYPE_ON_OFF, R.layout.item_device_on_off);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_DEVICE:
                final RoomDeviceBean bean = (RoomDeviceBean) item;
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (bean.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
        }
    }

}
