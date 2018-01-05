package com.meilun.security.smart.cateye.view;

import com.Player.Source.TVideoFile;
import com.chad.library.adapter.base.BaseViewHolder;
import com.meilun.security.smart.R;

import java.util.List;

import cn.itsite.abase.cache.CacheManager;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/5/4 0004 20:55.
 * Email: liujia95me@126.com
 */

public class RecordRVAdapter extends BaseRecyclerViewAdapter<TVideoFile, BaseViewHolder> {

    public RecordRVAdapter(List<TVideoFile> data) {
        super(R.layout.item_cateye_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TVideoFile item) {
        helper.setText(R.id.tv_create_time, item.syear + "-" + item.smonth + "-" + item.sday + "\n" + item.shour + ":" + item.sminute + ":" + item.ssecond)
                .setVisible(R.id.tv_file_size, true)
                .setText(R.id.tv_file_size, CacheManager.getFormatSize(item.nFileSize))
                .setText(R.id.tv_no, (helper.getLayoutPosition() + 1)+"");
    }
}
