package com.aglhz.s1.camera;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.macrovideo.sdk.custom.DeviceInfo;
import com.macrovideo.sdk.tools.DeviceScanner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/9/6 0006 10:14.
 * Email: liujia95me@126.com
 */

public class CameraScanFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;

    Unbinder unbinder;
    private int countDown = 60;

    public static CameraScanFragment newInstance() {
        CameraScanFragment fragment = new CameraScanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_scan, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
        toolbarTitle.setText("正在配置");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        Animation rotate = AnimationUtils.loadAnimation(_mActivity, R.anim.rotate_scan_video);
        ivScan.startAnimation(rotate);
        scan();
    }

    private void scan() {
        ivScan.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<DeviceInfo> deviceInfos = DeviceScanner.getDeviceListFromLan();
                ALog.e(TAG, "扫描-->" + System.currentTimeMillis() + " --->" + deviceInfos.size());
                tvCountDown.setText((countDown--) + "");
                if (deviceInfos.size() > 0 || countDown == 0) {
                    return;
                }
                scan();
            }
        }, 1000);
    }

    private void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
