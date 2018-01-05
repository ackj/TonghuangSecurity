package com.meilun.security.smart.cateye.view;

import com.Player.web.response.DevItemInfo;
import com.chad.library.adapter.base.BaseViewHolder;
import com.meilun.security.smart.R;

import java.util.List;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/4 0004 18:46.
 * Email: liujia95me@126.com
 */

public class QueryRVAdapter extends BaseRecyclerViewAdapter<DevItemInfo, BaseViewHolder> {

    public static final String TAG = QueryRVAdapter.class.getSimpleName();

    public QueryRVAdapter(List<DevItemInfo> data) {
        super(R.layout.item_cateye_query_device, data);
    }

    private int selectIndex;

    public void setSelectedItem(int selectIndex) {
        this.selectIndex = selectIndex;
        ALog.e(TAG, "setSelectedItem:" + selectIndex);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, DevItemInfo item) {
//        helper.setImageResource(R.id.iv_img_item_query_device, item.imgRes)
//                .setText(R.id.tv_name_item_query_device, item.name);
        helper.setText(R.id.tv_name_item_query_device, item.node_name)
                .setChecked(R.id.radiobutton, (helper.getLayoutPosition() - 1) == selectIndex);

        helper.getView(R.id.radiobutton).setOnClickListener(v -> {
            selectIndex = helper.getLayoutPosition() - 1;
            notifyDataSetChanged();
        });
    }
}
