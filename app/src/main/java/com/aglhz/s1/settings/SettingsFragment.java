package com.aglhz.s1.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aglhz.s1.R;
import com.aglhz.s1.adapter.SettingsAdapter;
import com.aglhz.s1.bean.SettingsBean;


import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 17:27.
 * Email: liujia95me@126.com
 */

public class SettingsFragment extends SupportFragment {

    private RecyclerView recyclerView;

    public static SupportFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private void initData(){
        List<SettingsBean> settingsBeans = new ArrayList<>();
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "报警延时", "5s"));
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        SettingsAdapter adapter = new SettingsAdapter(settingsBeans);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {

    }
}
