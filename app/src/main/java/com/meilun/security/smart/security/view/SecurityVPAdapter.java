package com.meilun.security.smart.security.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/12/25 0025 09:36.
 * Email: liujia95me@126.com
 */

public class SecurityVPAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragmentss;

    public SecurityVPAdapter(FragmentManager fm, List<BaseFragment> fragmentss) {
        super(fm);
        this.fragmentss = fragmentss;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentss.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "智能控制" : "智能安防";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
