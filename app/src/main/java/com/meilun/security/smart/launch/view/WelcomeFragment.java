package com.meilun.security.smart.launch.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.meilun.security.smart.R;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.main.MainActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.cache.SPCache;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class WelcomeFragment extends BaseFragment {
    public static final String TAG = WelcomeFragment.class.getSimpleName();
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.btn_begin)
    Button btnBegin;
    @BindView(R.id.indicator_welcome_fragment)
    MagicIndicator indicator;
    private Unbinder unbinder;
    Integer[] res = {
            R.drawable.bg_yingdao1ye_1242px_2208px,
            R.drawable.bg_yingdao2ye_1242px_2208px,
            R.drawable.bg_yingdao3ye_1242px_2208px};

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
        initIndicator();
    }

    private void initData() {
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return res.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = new ImageView(_mActivity);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(_mActivity)
                        .load(res[position])
                        .into(iv);
                container.addView(iv);
                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == res.length - 1) {
                    btnBegin.setVisibility(View.VISIBLE);
                } else {
                    btnBegin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicator() {
        CircleNavigator circleNavigator = new CircleNavigator(_mActivity);
        circleNavigator.setCircleCount(res.length);
        circleNavigator.setCircleColor(ContextCompat.getColor(_mActivity, R.color.base_color));
        circleNavigator.setCircleClickListener(index -> viewPager.setCurrentItem(index));
        indicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(indicator, viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_begin)
    public void onViewClicked() {
        SPCache.put(_mActivity, Constants.IS_FIRST_ENTER, false);
        startActivity(new Intent(_mActivity, MainActivity.class));
        _mActivity.overridePendingTransition(0, 0);
        //此处之所以延迟退出是因为立即退出在小米手机上会有一个退出跳转动画，而我不想要这个垂直退出的跳转动画。
        new Handler().postDelayed(() -> _mActivity.finish(), 1000);
    }
}
