package com.aglhz.s1.history.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.HistoryBean;
import com.aglhz.s1.history.HistoryRVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 16:13.
 * Email: liujia95me@126.com
 */

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("历史记录");
    }

    private void initData() {
        List<HistoryBean> historyBeans = new ArrayList<>();
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        historyBeans.add(new HistoryBean("F0F0F0F0F00F", "Door门窗打开", "2017-03-07 11:47"));
        HistoryRVAdapter adapter = new HistoryRVAdapter(historyBeans);
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerview.setAdapter(adapter);
    }

    private void initListener() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
