package com.aglhz.s1.more.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.gateway.view.GatewayListFragment;
import com.aglhz.s1.net.view.SetWifiFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 17:27.
 * Email: liujia95me@126.com
 */

public class MoreFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

    public static SupportFragment newInstance() {
        return new MoreFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
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
    }

    private void initListener() {
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (position) {
//                    case 0:
//
//                        break;
//                    case 1:
//                        _mActivity.start(SetWifiFragment.newInstance());
//                        break;
//                    case 2:
//                        _mActivity.start(AddHostFragment.newInstance());
//                        break;
//                    case 3:
//                        _mActivity.start(HostSettingsFragment.newInstance());
//                        break;
//                    case 4:
//                        _mActivity.start(RoomManagerFragment.newInstance());
//                        break;
//                    case 5:
//                        break;
//                    case 6:
//                        break;
//                    case 7:
////                        _mActivity.start(LoginFragment.newInstance());
//
//                        _mActivity.startActivity(new Intent(_mActivity, NetActivity.class));
//
//
//                        break;
//                }
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_room_manager, R.id.ll_host_setting, R.id.ll_wifi_setting, R.id.ll_add_host, R.id.ll_host_manager, R.id.ll_about, R.id.ll_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_room_manager:
                _mActivity.start(RoomManagerFragment.newInstance());
                break;
            case R.id.ll_host_setting:
                _mActivity.start(HostSettingsFragment.newInstance());
                break;
            case R.id.ll_wifi_setting:
                _mActivity.start(SetWifiFragment.newInstance());
                break;
            case R.id.ll_add_host:
                _mActivity.start(AddHostFragment.newInstance());
                break;
            case R.id.ll_host_manager:
                _mActivity.start(GatewayListFragment.newInstance());
                break;
            case R.id.ll_about:
                break;
            case R.id.ll_logout:
                break;
        }
    }
}
