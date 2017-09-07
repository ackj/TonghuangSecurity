package com.aglhz.s1.camera;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.macrovideo.sdk.custom.DeviceInfo;
import com.macrovideo.sdk.defines.Defines;
import com.macrovideo.sdk.media.LoginHandle;
import com.macrovideo.sdk.media.NVMediaPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/9/6 0006 10:13.
 * Email: liujia95me@126.com
 */

public class CameraPlayFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private LoginHandle _deviceParam = null;
    private NVMediaPlayer mvMediaPlayer = null;
    private boolean m_bReversePRI = true;

    private String m_strName = "31011103";
    private int mStreamType = 0;//当前播放的质量
    private DeviceInfo deviceTest = new DeviceInfo(-1, 30754848, "30754848",
            "", 8800, "admin", "", "unkown mac addr",
            "30754848.nvdvr.net", Defines.SERVER_SAVE_TYPE_ADD);

    Unbinder unbinder;

    public static CameraPlayFragment newInstance() {
        CameraPlayFragment fragment = new CameraPlayFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_play, container, false);
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
        toolbarTitle.setText("智能监控");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    //开始播放
    private void startPlay() {
        if (_deviceParam == null || mvMediaPlayer == null) return;
        toolbarTitle.setText(getString(R.string.Notification_Playing_Chn) + " " + m_strName);
        mvMediaPlayer.EnableRender();
        mvMediaPlayer.StartPlay(0, 0, mStreamType, true, _deviceParam);
        mvMediaPlayer.setReverse(false);//反转
        mvMediaPlayer.playAudio();
    }

    private void initData() {
    }

    private void initListener() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
