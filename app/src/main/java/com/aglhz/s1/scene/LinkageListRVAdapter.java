package com.aglhz.s1.scene;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.SceneBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/31 0031 09:50.
 * Email: liujia95me@126.com
 */

public class LinkageListRVAdapter extends BaseRecyclerViewAdapter<SceneBean, BaseViewHolder> {

    public LinkageListRVAdapter(List<SceneBean> data) {
        super(R.layout.item_intelligence_linkage, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SceneBean item) {
        helper.setText(R.id.tv_scene_item_scene, item.scene)
                .addOnClickListener(R.id.ll_item_intelligence_linkage)
                .addOnClickListener(R.id.tv_delete_item_scene);
    }
}
