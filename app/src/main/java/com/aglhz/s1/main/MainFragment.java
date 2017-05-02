package com.aglhz.s1.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.R;
import com.aglhz.s1.history.HistoryFragment;
import com.aglhz.s1.room.RoomFragment;
import com.aglhz.s1.scene.SceneFragment;
import com.aglhz.s1.security.SecurityFragment;
import com.aglhz.s1.settings.SettingsFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 11:12.
 * Email: liujia95me@126.com
 */

public class MainFragment extends SupportFragment {

    private AHBottomNavigation ahbn;
    private SupportFragment[] fragments = new SupportFragment[5];
    private int bottomNavigationPreposition;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ahbn = (AHBottomNavigation) view.findViewById(R.id.ahbn_main_fragment);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            fragments[0] = SecurityFragment.newInstance();
            fragments[1] = RoomFragment.newInstance();
            fragments[2] = SceneFragment.newInstance();
            fragments[3] = HistoryFragment.newInstance();
            fragments[4] = SettingsFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container_main_fragment, 0, fragments[0], fragments[1], fragments[2], fragments[3],fragments[4]);
        } else {
            fragments[0] = findFragment(SecurityFragment.class);
            fragments[1] = findFragment(RoomFragment.class);
            fragments[2] = findFragment(SceneFragment.class);
            fragments[3] = findFragment(HistoryFragment.class);
            fragments[4] = findFragment(SettingsFragment.class);
        }
        initData();
        initListener();
    }

    private void initData() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.security, R.mipmap.ic_launcher, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.room, R.mipmap.ic_launcher, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.scene, R.mipmap.ic_launcher, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.hisotry, R.mipmap.ic_launcher, R.color.white);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.more, R.mipmap.ic_launcher, R.color.white);
        List<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);
        ahbn.addItems(bottomNavigationItems);
        ahbn.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        ahbn.setBehaviorTranslationEnabled(false);
        ahbn.setColored(true);
        ahbn.setForceTint(false);
        ahbn.setAccentColor(getResources().getColor(R.color.base_color));
        ahbn.setInactiveColor(getResources().getColor(R.color.black));
        ahbn.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        ahbn.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, final boolean wasSelected) {
                showHideFragment(fragments[position], fragments[bottomNavigationPreposition]);
                bottomNavigationPreposition = position;
                return true;
            }
        });
        ahbn.setCurrentItem(0);
    }

    private void initListener() {
    }
}
