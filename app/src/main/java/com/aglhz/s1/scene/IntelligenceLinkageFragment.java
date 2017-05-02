package com.aglhz.s1.scene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aglhz.s1.R;
import com.aglhz.s1.adapter.SceneListAdapter;
import com.aglhz.s1.bean.SceneBean;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 14:42.
 * Email: liujia95me@126.com
 */

public class IntelligenceLinkageFragment extends SupportFragment{

    private RecyclerView recyclerview;

    public static IntelligenceLinkageFragment newInstance() {
        return new IntelligenceLinkageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intelligence_linkage, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private void initData() {
        List<SceneBean> sceneBeans = new ArrayList<>();
        sceneBeans.add(new SceneBean("洗澡", false));
        sceneBeans.add(new SceneBean("洗澡", true));
        sceneBeans.add(new SceneBean("洗澡", true));
        sceneBeans.add(new SceneBean("洗澡", true));
        sceneBeans.add(new SceneBean("起床", true));
        SceneListAdapter adapter = new SceneListAdapter(sceneBeans);
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerview.setAdapter(adapter);
    }

    private void initListener() {

    }
}
