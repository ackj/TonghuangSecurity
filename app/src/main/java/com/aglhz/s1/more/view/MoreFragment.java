package com.aglhz.s1.more.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.UserHelper;
import com.aglhz.s1.event.EventLogin;
import com.aglhz.s1.gateway.view.GatewayListFragment;
import com.aglhz.s1.login.LoginActivity;
import com.aglhz.s1.net.view.SetWifiFragment;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 17:27.
 * Email: liujia95me@126.com
 */

public class MoreFragment extends BaseFragment {
    public static final String TAG = MoreFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;

    public static SupportFragment newInstance() {
        return new MoreFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("更多");
    }

    private void initData() {
        updataView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.ll_room_manager,
            R.id.ll_host_setting,
            R.id.ll_wifi_setting,
            R.id.ll_add_host,
            R.id.ll_host_manager,
            R.id.ll_about,
            R.id.ll_logout})
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
                startActivity(new Intent(_mActivity, LoginActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(EventLogin event) {
        updataView();
    }

    private void updataView() {

        if (UserHelper.isLogined()) {
            Glide.with(this)
                    .load(UserHelper.userInfo.getFace())
                    .placeholder(R.drawable.ic_more_avatar_black_180px)
                    .error(R.drawable.ic_more_avatar_black_180px)
                    .bitmapTransform(new CropCircleTransformation(_mActivity))
                    .into(ivAvatar);

            tvNickname.setText(UserHelper.userInfo.getNickName());
            tvPhoneNumber.setText(UserHelper.userInfo.getMobile());
        }
    }
}
