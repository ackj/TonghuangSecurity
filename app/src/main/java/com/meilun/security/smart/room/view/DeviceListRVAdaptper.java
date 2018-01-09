package com.meilun.security.smart.room.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.meilun.security.smart.R;
import com.meilun.security.smart.entity.bean.MainDeviceBean;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author: LiuJia on 2018/1/3 0003 09:46.
 * Email: liujia95me@126.com
 */

public class DeviceListRVAdaptper extends BaseRecyclerViewAdapter<List<MainDeviceBean>, BaseViewHolder> {

    public DeviceListRVAdaptper() {
        super(R.layout.item_cateye);
    }

    SupportActivity activity;

    public void setActivity(SupportActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<MainDeviceBean> item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
        DeviceListItemRVAdapter adapter = new DeviceListItemRVAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(item);
        helper.setVisible(R.id.ll_layout, item.size() > 0)
                .setText(R.id.tv_title,helper.getLayoutPosition() == 0?"智能猫眼":"智能监控");

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                if (helper.getLayoutPosition() == 0) {
                    listener.onClickCateye(adapter.getItem(position));
                } else if (helper.getLayoutPosition() == 1) {
                    listener.onClickCamera(adapter.getItem(position));
                }
            }
        });

        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter1, View view, int position) {
                if (helper.getLayoutPosition() == 1) {
                    listener.onClickLongCamera(adapter.getItem(position));
                }
                return false;
            }
        });
    }

    private OnDeviceItemClickListener listener;

    public void setOnDeviceItemClickListener(OnDeviceItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnDeviceItemClickListener {
        void onClickCamera(MainDeviceBean bean);

        void onClickCateye(MainDeviceBean bean);

        void onClickLongCamera(MainDeviceBean bean);
    }
}
