package com.aglhz.s1.security;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aglhz.s1.R;
import com.aglhz.s1.adapter.SecurityAdapter;
import com.aglhz.s1.bean.SecurityBean;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;


/**
 * Author: LiuJia on 2017/4/26 0026 11:11.
 * Email: liujia95me@126.com
 */

public class SecurityFragment extends BaseFragment {

    private RecyclerView recyclerView;

    public static SecurityFragment newInstance() {
        return new SecurityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_security, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity,4));
        List<SecurityBean> data = new ArrayList<>();
        data.add(new SecurityBean(R.mipmap.ic_launcher,"门槛"));
        data.add(new SecurityBean(R.mipmap.ic_launcher,"红外"));
        data.add(new SecurityBean(R.mipmap.ic_launcher,"红外对射"));
        data.add(new SecurityBean(R.mipmap.ic_launcher,"玻璃破碎"));
        data.add(new SecurityBean(R.mipmap.ic_launcher,"漏水"));
        data.add(new SecurityBean(R.mipmap.ic_launcher,"紧急按钮"));
        data.add(new SecurityBean(R.mipmap.ic_launcher,"气体"));
        data.add(new SecurityBean(R.mipmap.ic_launcher,"添加探测器"));

        SecurityAdapter adapter = new SecurityAdapter(data);
        View headerView = LayoutInflater.from(_mActivity).inflate(R.layout.item_security_header, null);
        adapter.setHeaderView(headerView);
        recyclerView.setAdapter(adapter);

    }

    private void initListener() {

    }
}
