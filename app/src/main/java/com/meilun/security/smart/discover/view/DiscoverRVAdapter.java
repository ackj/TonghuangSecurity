package com.meilun.security.smart.discover.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.meilun.security.smart.App;
import com.meilun.security.smart.R;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.entity.bean.DiscoverBean;
import com.meilun.security.smart.entity.bean.DiscoverHomeBean;
import com.meilun.security.smart.smarthome.view.SmartHomeMallFragment;
import com.meilun.security.smart.web.WebActivity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
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

    private boolean isHome;

    public void switchGatewayIsHome(boolean isHome) {
        this.isHome = isHome;
        notifyItemChanged(2);
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
                    banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            DiscoverBean.DataBean.AdvsBean bean = banners.get(position);
                            gotoWeb(bean.getTitle(), bean.getLink());
                        }
                    });
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
                        .addOnClickListener(R.id.view_click);
                SimpleMarqueeView smvNotice = helper.getView(R.id.smv_notice);
                SimpleMF<String> marqueeFactory = new SimpleMF(App.mContext);
                marqueeFactory.setData(item.notices);
                smvNotice.setMarqueeFactory(marqueeFactory);
                smvNotice.startFlipping();
                break;
            case DiscoverHomeBean.TYPE_BUTTONS:
                helper.setText(R.id.tv_status, isHome ? "在家布防" : "离家布防")
                        .setImageResource(R.id.iv_status, isHome ? R.drawable.ic_zjbf_150px : R.drawable.ic_ljbf_150px);
                helper.addOnClickListener(R.id.ll_company)
                        .addOnClickListener(R.id.ll_switch_gateway)
                        .addOnClickListener(R.id.ll_camera)
                        .addOnClickListener(R.id.ll_store);
                break;
            case DiscoverHomeBean.TYPE_NEWS:
                helper.addOnClickListener(R.id.tv_more);
                LinearLayout llLayout = helper.getView(R.id.layout_news);
                if (item.news == null || item.news.size() == 0) {
                    llLayout.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                } else {
                    llLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
                RecyclerView recyclerView = helper.getView(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(App.mContext));
                NewsRVAdapter newsRVAdapter = new NewsRVAdapter(item.news);
                recyclerView.setAdapter(newsRVAdapter);
                newsRVAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                        DiscoverBean.DataBean.NewsBean bean = item.news.get(position);
                        gotoWeb(bean.getTitle(), bean.getContent());
                    }
                });
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

    private void gotoWeb(String title, String link) {
        Intent intent = new Intent(fragment.getActivity(), WebActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_LINK, link);
        fragment.getActivity().startActivity(intent);//点击一个商品跳WEB
    }
}
