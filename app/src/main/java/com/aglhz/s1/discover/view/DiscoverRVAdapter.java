package com.aglhz.s1.discover.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.DiscoverBean;
import com.aglhz.s1.entity.bean.DiscoverHomeBean;
import com.aglhz.s1.smarthome.view.SmartHomeMallFragment;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * Author: LiuJia on 2017/11/21 0021 17:37.
 * Email: liujia95me@126.com
 */

public class DiscoverRVAdapter extends BaseMultiItemQuickAdapter<DiscoverHomeBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DiscoverRVAdapter(List<DiscoverHomeBean> data) {
        super(data);
        addItemType(DiscoverHomeBean.TYPE_BANNER, R.layout.item_discover_fragment_rv_banner);
        addItemType(DiscoverHomeBean.TYPE_NOTICE, R.layout.item_discover_fragment_rv_notice);
        addItemType(DiscoverHomeBean.TYPE_BUTTONS, R.layout.item_discover_fragment_rv_function);
        addItemType(DiscoverHomeBean.TYPE_NEWS, R.layout.item_discover_fragment_rv_news);
        addItemType(DiscoverHomeBean.TYPE_STORE, R.layout.item_discover_fragment_rv_life);
    }

    private DiscoverFragment fragment;

    public void setFragment(DiscoverFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverHomeBean item) {
        switch (helper.getLayoutPosition()) {
            case DiscoverHomeBean.TYPE_BANNER:
                Banner banner = helper.getView(R.id.viewpager_item_banner);
                List<DiscoverBean.DataBean.AdvsBean> banners = item.bannerss;
                if (banners != null && banners.size() > 0) {
                    banner.setImages(banners).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            DiscoverBean.DataBean.AdvsBean bean = (DiscoverBean.DataBean.AdvsBean) path;
                            Glide.with(context).
                                    load(bean.getCover())
                                    .error(R.drawable.bg_normal_banner_1200px_800px)
                                    .placeholder(R.drawable.bg_normal_banner_1200px_800px)
                                    .into(imageView);
                        }
                    }).start();
                } else {
                    List<Integer> normal = new ArrayList<>();
                    normal.add(R.drawable.bg_normal_banner_1200px_800px);
                    banner.setImages(normal).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).
                                    load((Integer) path)
                                    .into(imageView);
                        }
                    }).start();
                }
                break;
            case DiscoverHomeBean.TYPE_NOTICE:
                helper.addOnClickListener(R.id.ll_item_notice)
                        .addOnClickListener(R.id.smv_notice);

                SimpleMarqueeView smvNotice = helper.getView(R.id.smv_notice);
                SimpleMF<String> marqueeFactory = new SimpleMF(App.mContext);
                marqueeFactory.setData(item.notices);
                smvNotice.setMarqueeFactory(marqueeFactory);
                smvNotice.startFlipping();
                break;
            case DiscoverHomeBean.TYPE_BUTTONS:
                helper.addOnClickListener(R.id.ll_quick_open_door)
                        .addOnClickListener(R.id.ll_property_payment)
                        .addOnClickListener(R.id.ll_temporary_parking)
                        .addOnClickListener(R.id.llayout_camera_list);
                break;
            case DiscoverHomeBean.TYPE_NEWS:
                helper.addOnClickListener(R.id.tv_more);
                RecyclerView recyclerView = helper.getView(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(App.mContext));
                recyclerView.setAdapter(new NewsRVAdapter(item.news));
                break;
            case DiscoverHomeBean.TYPE_STORE:
                helper.setText(R.id.tv_title, "安防商城");
                RecyclerView rvSmartLife = helper.getView(R.id.recyclerView);
                rvSmartLife.setLayoutManager(new LinearLayoutManager(App.mContext, LinearLayoutManager.HORIZONTAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                SmartLifeAdapter smartLifeAdapter = new SmartLifeAdapter(item.stores);
                smartLifeAdapter.setOnItemClickListener((adapter12, view, position) ->
                        ((BaseActivity) fragment.getActivity()).start(SmartHomeMallFragment.newInstance(item.stores, position)));
                rvSmartLife.setAdapter(smartLifeAdapter);
                break;
            default:
        }
    }
}
