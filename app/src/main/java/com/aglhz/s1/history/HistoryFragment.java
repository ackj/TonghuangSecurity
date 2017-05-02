package com.aglhz.s1.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aglhz.s1.R;
import com.aglhz.s1.adapter.HistoryAdapter;
import com.aglhz.s1.bean.HistoryBean;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 16:13.
 * Email: liujia95me@126.com
 */

public class HistoryFragment extends SupportFragment {

    private RecyclerView recyclerview;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
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
        List<HistoryBean> historyBeans = new ArrayList<>();
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        HistoryAdapter adapter = new HistoryAdapter(historyBeans);
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerview.setAdapter(adapter);
    }

    private void initListener() {

    }


}
