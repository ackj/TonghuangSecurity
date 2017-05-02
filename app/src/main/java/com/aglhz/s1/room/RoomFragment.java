package com.aglhz.s1.room;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.R;
import com.aglhz.s1.adapter.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 09:47.
 * Email: liujia95me@126.com
 */

public class RoomFragment extends SupportFragment {

    private TabLayout tabLayout;
    private ViewPager viewpager;

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private void initData() {
        tabLayout.setupWithViewPager(viewpager);
        List<SupportFragment> fragments = new ArrayList<>();
        fragments.add(RoomDeviceFragment.newInstance());
        fragments.add(RoomDeviceFragment.newInstance());
        fragments.add(RoomDeviceFragment.newInstance());
        RoomAdapter adapter = new RoomAdapter(fragments,_mActivity.getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }

    private void initListener() {

    }
}
