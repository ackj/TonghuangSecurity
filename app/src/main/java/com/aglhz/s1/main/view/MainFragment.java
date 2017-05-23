package com.aglhz.s1.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.R;
import com.aglhz.s1.history.view.HistoryFragment;
import com.aglhz.s1.room.view.RoomFragment2;
import com.aglhz.s1.scene.view.SceneFragment;
import com.aglhz.s1.security.view.SecurityFragment;
import com.aglhz.s1.settings.view.SettingsFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 11:12.
 * Email: liujia95me@126.com
 */

public class MainFragment extends BaseFragment {

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
            fragments[1] = RoomFragment2.newInstance();
            fragments[2] = SceneFragment.newInstance();
            fragments[3] = HistoryFragment.newInstance();
            fragments[4] = SettingsFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container_main_fragment, 0, fragments[0], fragments[1], fragments[2], fragments[3], fragments[4]);
        } else {
            fragments[0] = findFragment(SecurityFragment.class);
            fragments[1] = findFragment(RoomFragment2.class);
            fragments[2] = findFragment(SceneFragment.class);
            fragments[3] = findFragment(HistoryFragment.class);
            fragments[4] = findFragment(SettingsFragment.class);
        }
        initData();
        initListener();
    }

    private void initData() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.security, R.drawable.ic_navigationsecurity_black_78px, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.room, R.drawable.ic_navigationroom_black_78px, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.scene, R.drawable.ic_navigationscenes_black_78px, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.hisotry, R.drawable.ic_navigationhistory_black_78px, R.color.white);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.more, R.drawable.ic_navigationmore_black_78px, R.color.white);
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
