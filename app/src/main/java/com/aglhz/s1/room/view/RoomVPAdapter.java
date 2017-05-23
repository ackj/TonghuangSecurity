package com.aglhz.s1.room.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aglhz.s1.R;
import com.bumptech.glide.Glide;

/**
 * Author: LiuJia on 2017/5/17 0017 19:53.
 * Email: liujia95me@126.com
 */

public class RoomVPAdapter extends PagerAdapter {

    private int[] backgroundRes = {
            R.drawable.bg_background_hall_1242px_2047px
            , R.drawable.bg_background_room_1242px_2047px
            , R.drawable.bg_background_wc_1242px_2047px
            , R.drawable.bg_background_kitchen_1242px_2047px};

    @Override
    public int getCount() {
        return backgroundRes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(container.getContext());
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(container.getContext())
                .load(backgroundRes[position])
                .into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
