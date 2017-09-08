package com.aglhz.s1.camera;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.macrovideo.sdk.defines.Defines;
import com.macrovideo.sdk.media.LibContext;
import com.macrovideo.sdk.media.LoginHandle;
import com.macrovideo.sdk.media.NVMediaPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ToastUtils;

/**
 * Author: LiuJia on 2017/9/6 0006 10:13.
 * Email: liujia95me@126.com
 */

public class CameraPlayFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_video_play_container)
    FrameLayout flVideoPlayContainer;

    private int nScreenOrientation = Configuration.ORIENTATION_PORTRAIT;

    private NVMediaPlayer mvMediaPlayer = null;
    private boolean m_bReversePRI = true;

    private String m_strName = "31011103";
    private int mStreamType = 0;//当前播放的质量
    private LoginHandle deviceInfo;
    Unbinder unbinder;
    private boolean isSpeak = false;

    public static CameraPlayFragment newInstance(LoginHandle deviceInfo) {
        CameraPlayFragment fragment = new CameraPlayFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("device_info", deviceInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_play, container, false);
        unbinder = ButterKnife.bind(this, view);
        deviceInfo = getArguments().getParcelable("device_info");
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
        if (deviceInfo == null || mvMediaPlayer == null) return;
        toolbarTitle.setText(getString(R.string.Notification_Playing_Chn) + " " + m_strName);
        mvMediaPlayer.EnableRender();
        mvMediaPlayer.StartPlay(0, 0, mStreamType, true, deviceInfo);
        mvMediaPlayer.setReverse(false);//反转
        mvMediaPlayer.playAudio();
    }

    private void initData() {
        //初始化
        mvMediaPlayer = new NVMediaPlayer(_mActivity, nScreenOrientation, 0);
        mvMediaPlayer.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        //添加播放器到容器
        flVideoPlayContainer.addView(mvMediaPlayer);
        //	设置环境变量
        LibContext.SetContext(mvMediaPlayer, null, null, null);
        startPlay();
    }

    private void initListener() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_up, R.id.iv_left, R.id.iv_right, R.id.iv_down, R.id.ll_inversion, R.id.ll_talkback, R.id.ll_photograph, R.id.ll_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_up:
                mvMediaPlayer.SendPTZAction(false, false, true, false, 0);
                break;
            case R.id.iv_left:
                mvMediaPlayer.SendPTZAction(true, false, false, false, 0);
                break;
            case R.id.iv_right:
                mvMediaPlayer.SendPTZAction(false, true, false, false, 0);
                break;
            case R.id.iv_down:
                mvMediaPlayer.SendPTZAction(false, false, false, true, 0);
                break;
            case R.id.ll_inversion:
                mvMediaPlayer.SetCamImageOrientation(Defines.NV_IPC_ORIENTATION_REVERSE);
                break;
            case R.id.ll_talkback:
                if (isSpeak) {
                    mvMediaPlayer.StopSpeak();
                    ToastUtils.showToast(_mActivity,"对讲停止");
                } else {
                    mvMediaPlayer.StartSpeak();
                    ToastUtils.showToast(_mActivity,"开启对讲，再次点击停止对讲");
                }
                isSpeak = !isSpeak;
                break;
            case R.id.ll_photograph:
                Bitmap screenshot = mvMediaPlayer.Screenshot();

                break;
            case R.id.ll_video:
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        ALog.e(TAG, "onStop");
        LibContext.stopAll();//add by luo 20141219
    }

    @Override
    public void onPause() {
        super.onPause();
        mvMediaPlayer.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mvMediaPlayer.onResume();
    }
}
