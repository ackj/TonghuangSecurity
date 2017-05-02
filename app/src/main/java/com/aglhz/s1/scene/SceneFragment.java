package com.aglhz.s1.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.aglhz.s1.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 14:07.
 * Email: liujia95me@126.com
 */

public class SceneFragment extends SupportFragment {

    private TabLayout tabLayout;
    private FrameLayout frameLayout;

    private SupportFragment[] fragments = new SupportFragment[2];

    public static SceneFragment newInstance() {
        return new SceneFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scene, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_scene);
        frameLayout = (FrameLayout) view.findViewById(R.id.framelayout_scene);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            fragments[0] = IntelligenceSceneFragment.newInstance();
            fragments[1] = IntelligenceLinkageFragment.newInstance();
            loadMultipleRootFragment(R.id.framelayout_scene, 0, fragments[0], fragments[1]);
        } else {
            fragments[0] = findFragment(IntelligenceSceneFragment.class);
            fragments[1] = findFragment(IntelligenceLinkageFragment.class);
        }
        initData();
        initListener();
    }

    private void initData() {
        tabLayout.addTab(tabLayout.newTab().setText("智能场景"));
        tabLayout.addTab(tabLayout.newTab().setText("智能联动"));
    }

    private void initListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int hidePosition = tab.getPosition() == 0 ? 1 : 0;
                Log.d("onTabSelected","hidePosition:"+hidePosition);
                showHideFragment(fragments[tab.getPosition()], fragments[hidePosition]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
