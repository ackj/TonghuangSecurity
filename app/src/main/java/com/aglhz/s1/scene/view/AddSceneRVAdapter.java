package com.aglhz.s1.scene.view;

import android.widget.ImageView;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/8/12 0012 16:56.
 * Email: liujia95me@126.com
 */

public class AddSceneRVAdapter extends BaseRecyclerViewAdapter<DeviceListBean.DataBean.SubDevicesBean, BaseViewHolder> {

    public AddSceneRVAdapter() {
        super(R.layout.item_rv_add_scene);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceListBean.DataBean.SubDevicesBean item) {
        helper.setText(R.id.tv_name_item_rv_add_scene_fragment, item.getName());
        Glide.with(App.mContext)
                .load(item.getIcon())
                .into((ImageView) helper.getView(R.id.iv_icon_item_rv_add_scene_fragment));
    }
}
