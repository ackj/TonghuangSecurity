package com.aglhz.s1.settings.view;

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
import com.aglhz.s1.bean.SettingsBean;
import com.aglhz.s1.settings.SettingsRVAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 17:27.
 * Email: liujia95me@126.com
 */

public class SettingsFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private SettingsRVAdapter adapter;

    public static SupportFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
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
        toolbarTitle.setText("更多");
    }

    private void initData() {
        List<SettingsBean> settingsBeans = new ArrayList<>();
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "基本信息", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "WIFI设置", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "添加主机", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "主机设置", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "房间管理", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "主机管理", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "关于", "5s"));
        settingsBeans.add(new SettingsBean(R.mipmap.ic_launcher, "注销登陆", "5s"));
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new SettingsRVAdapter(settingsBeans);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        _mActivity.start(SetWifiFragment.newInstance());
                        break;
                    case 2:
                        _mActivity.start(AddHostFragment.newInstance());
                        break;
                    case 3:
                        _mActivity.start(HostSettingsFragment.newInstance());
                        break;
                    case 4:
                        _mActivity.start(RoomManagerFragment.newInstance());
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
