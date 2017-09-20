package com.aglhz.s1.camera;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aglhz.s1.R;
import com.aglhz.s1.camera.contract.CameraSettingContract;
import com.aglhz.s1.camera.presenter.CameraSettingPresenter;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.CameraBean;
import com.aglhz.s1.event.EventCameraListRefresh;
import com.p2p.core.BaseMonitorActivity;
import com.p2p.core.P2PHandler;
import com.p2p.core.P2PValue;
import com.p2p.core.P2PView;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.utils.ToastUtils;
import rx.functions.Action1;

/**
 * Author: LiuJia on 2017/9/13 0013 17:53.
 * Email: liujia95me@126.com
 */

public class CameraPlayActivity extends BaseMonitorActivity implements CameraSettingContract.View {

    private static final String TAG = CameraPlayActivity.class.getSimpleName();

    private static final int VIDEO_MODE_SD = 0;
    private static final int VIDEO_MODE_HD = 1;
    private static final int VIDEO_MODE_LD = 2;

    @BindView(R.id.rl_p2pview)
    RelativeLayout rlP2pview;
    @BindView(R.id.ll_control)
    LinearLayout llControl;
    @BindView(R.id.ll_talkback)
    LinearLayout llTalkback;
    @BindView(R.id.tv_quality)
    TextView tvQuality;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.tv_sound_no_off)
    TextView tvSoundNoOff;
    @BindView(R.id.view_black)
    View viewBlack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.tv_talk)
    TextView tvTalk;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_video)
    ImageView ivVideo;
    @BindView(R.id.iv_photograph)
    ImageView ivPhotograph;
    @BindView(R.id.iv_mute)
    ImageView ivMute;
    @BindView(R.id.iv_microphone)
    ImageView ivMicrophone;

    private String[] qualityArr = {"标清", "高清", "流畅"};
    private int recordFlag = 0;
    private String pathName = "";
    private String callID;
    private CameraBean.DataBean bean;
    private String updatePwd;
    private String userId;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_play);
        ButterKnife.bind(this);
        initView();
        initToolbar();
        checkCamerPermission();
        regFilter();
        initData();
        initInputDialog();
        initListener();
    }

    private void initView() {
        pView = (P2PView) findViewById(R.id.p2pview);
        initP2PView(7, P2PView.LAYOUTTYPE_TOGGEDER);//7是设备类型(技威定义的)

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载...");
        progressDialog.setCancelable(false);
    }

    private void initToolbar() {
        toolbarTitle.setText("智能监控");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkCamerPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            Log.e(TAG, "已授予CAMERA权限");
                        } else {
                            // 未获取权限
                            ToastUtils.showToast(CameraPlayActivity.this, "您没有授权CAMERA权限，请在设置中打开授权");
                        }
                    }
                });
    }

    private boolean connect(String userId, String id, String pwd) {
        return P2PHandler.getInstance().call(userId, pwd, true, 1, id, "", "", 2, id);
    }

    private void initData() {
        bean = (CameraBean.DataBean) getIntent().getSerializableExtra("bean");
        SharedPreferences sp = getSharedPreferences("Account", MODE_PRIVATE);
        userId = sp.getString("userId", "");
        //经过转换后的设备密码
        password = P2PHandler.getInstance().EntryPassword(bean.getPassword());
        callID = bean.getNo();
        ALog.e(TAG, "CallOnClick  id:" + callID + " -- password:" + bean.getPassword() + " -- userId:" + userId + " -- pwd:" + password);
        progressDialog.show();
        connect(userId, password, callID);
    }

    private void initListener() {
        llTalkback.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ivMicrophone.setSelected(true);
                    tvTalk.setText("正在对讲");
                    setMute(false);
                    return true;
                case MotionEvent.ACTION_UP:
                    ivMicrophone.setSelected(false);
                    tvTalk.setText("对讲");
                    setMute(true);
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        P2PHandler.getInstance().finish();
        super.onDestroy();
    }

    @Override
    protected void onP2PViewSingleTap() {

    }

    @Override
    protected void onP2PViewFilling() {

    }

    @Override
    protected void onCaptureScreenResult(boolean isSuccess, int prePoint) {
        if (isSuccess) {
            ToastUtils.showToast(this, "拍照成功，照片已保存到系统相册");
        } else {
            ToastUtils.showToast(this, "拍照失败");
        }
    }

    @Override
    protected void onVideoPTS(long videoPTS) {

    }

    @Override
    public int getActivityInfo() {
        return 0;
    }

    @Override
    protected void onGoBack() {

    }

    @Override
    protected void onGoFront() {

    }

    @Override
    protected void onExit() {

    }

    public static String P2P_ACCEPT = "com.XXX.P2P_ACCEPT";
    public static String P2P_READY = "com.XXX.P2P_READY";
    public static String P2P_REJECT = "com.XXX.P2P_REJECT";

    private ProgressDialog progressDialog;

    public void regFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(P2P_REJECT);
        filter.addAction(P2P_ACCEPT);
        filter.addAction(P2P_READY);
        registerReceiver(mReceiver, filter);
    }

    private CameraSettingPresenter presenter = new CameraSettingPresenter(this);
    private Params params = Params.getInstance();

    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(P2P_ACCEPT)) {
                int[] type = intent.getIntArrayExtra("type");
                P2PView.type = type[0];
                P2PView.scale = type[1];
                ALog.e(TAG, "\n 监控数据接收 type:" + P2PView.type + " scale:" + P2PView.scale);
                ALog.e(TAG, "监控数据接收:");
                P2PHandler.getInstance().openAudioAndStartPlaying(1);//打开音频并准备播放，calllType与call时type一致
            } else if (intent.getAction().equals(P2P_READY)) {
                progressDialog.dismiss();
                ALog.e(TAG, "\n 监控准备,开始监控");
                ALog.e(TAG, "监控准备,开始监控");
                ToastUtils.showToast(CameraPlayActivity.this, "开始监控");
                pView.sendStartBrod();
                viewBlack.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewBlack.setVisibility(View.GONE);
                    }
                },2000);
            } else if (intent.getAction().equals(P2P_REJECT)) {
                progressDialog.dismiss();
                int reason_code = intent.getIntExtra("reason_code", -1);
                int code1 = intent.getIntExtra("exCode1", -1);//806363145
                int code2 = intent.getIntExtra("exCode2", -1);
                String reject = String.format("\n 监控挂断(reson:%d,code1:%d,code2:%d)", reason_code, code1, code2);
                ALog.e(TAG, reject);
                if (reason_code == 0) {
                    dialogPassword.show();
                } else if (reason_code == 6) {
                    new AlertDialog.Builder(CameraPlayActivity.this)
                            .setMessage("请先配网")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CameraPlayActivity.this.finish();
                                }
                            })
                            .show();
                } else if (reason_code == 10 || reason_code == 3) {
                    connect(userId, callID, password);
                } else {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(CameraPlayActivity.this)
                            .setMessage("连接失败，是否重新连接？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    progressDialog.show();
                                    connect(userId, callID, password);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CameraPlayActivity.this.finish();
                                }
                            })
                            .show();
                }
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.ll_sound, R.id.tv_quality, R.id.ll_photograph, R.id.ll_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sound:
                AudioManager manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                if (manager != null) {
                    if (ivMute.isSelected()) {
                        manager.setStreamVolume(AudioManager.STREAM_MUSIC, manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
                        tvSoundNoOff.setText("静音");
                        ivMute.setSelected(false);
                    } else {
                        manager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                        ivMute.setSelected(true);
                        tvSoundNoOff.setText("取消静音");
                    }
                }
                break;
            case R.id.tv_quality:
                new AlertDialog.Builder(this)
                        .setItems(qualityArr, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case VIDEO_MODE_SD:
                                        P2PHandler.getInstance().setVideoMode(P2PValue.VideoMode.VIDEO_MODE_SD);
                                        break;
                                    case VIDEO_MODE_HD:
                                        P2PHandler.getInstance().setVideoMode(P2PValue.VideoMode.VIDEO_MODE_HD);
                                        break;
                                    case VIDEO_MODE_LD:
                                        P2PHandler.getInstance().setVideoMode(P2PValue.VideoMode.VIDEO_MODE_LD);
                                        break;
                                }
                                tvQuality.setText(qualityArr[which]);
                            }
                        }).show();
                break;
            case R.id.ll_photograph:
                checkSDPermision();
                break;
            case R.id.ll_video:
                if (recordFlag == 0) {
                    //开始录像
                    startMoniterRecoding();
                    recordFlag = 1;
                    tvVideo.setText("停止录像");
                    ivVideo.setSelected(true);
                } else {
                    //停止录像
                    stopMoniterReocding();
                    recordFlag = 0;
                    tvVideo.setText("录像");
                    ivVideo.setSelected(false);
                }
                break;
        }
    }

    private void checkSDPermision() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            int d = P2PHandler.getInstance().setScreenShotpath(Environment.getExternalStorageDirectory().getPath(), "123.jpg");
                            Log.e("dxsTest", "d:" + d);
                            captureScreen(-1);
                            Log.e("MonitoerActivity", "已授予STORAGE权限");
                        } else {
                            // 未获取权限
                            ToastUtils.showToast(CameraPlayActivity.this, "您没有授权STORAGE权限，请在设置中打开授权");
                        }
                    }
                });
    }

    public void startMoniterRecoding() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                    Environment.getExternalStorageState().equals(Environment.MEDIA_SHARED)) {
                String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "gwellvideorec" + File.separator + callID;
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                long time = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());// 制定日期的显示格式
                String name = "gwell" + "_" + sdf.format(new Date(time));
                pathName = file.getPath() + File.separator + name + ".mp4";
            } else {
                throw new NoSuchFieldException("sd卡");
            }
        } catch (NoSuchFieldException | NullPointerException e) {
            Toast.makeText(CameraPlayActivity.this, " 没有内存卡", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        Log.e("dxsTest", "pathName:" + pathName);
        if (P2PHandler.getInstance().starRecoding(pathName)) {
            ToastUtils.showToast(CameraPlayActivity.this, " 正在录像");
        } else {
            //录像初始化失败
            ToastUtils.showToast(CameraPlayActivity.this, " 初始化录像失败");
        }
    }

    public void stopMoniterReocding() {
        if (P2PHandler.getInstance().stopRecoding() == 0) {
            //录像不正常
            ToastUtils.showToast(CameraPlayActivity.this, " 录像不正常");
        } else {
            //正常停止
            ToastUtils.showToast(CameraPlayActivity.this, " 停止录像，已保存到" + pathName);
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ToastUtils.showToast(this, "修改失败");
    }

    @Override
    public void complete(Object response) {

    }

    @Override
    public void responseSuccess(BaseBean baseBean) {
        ToastUtils.showToast(this, "修改成功");
        EventBus.getDefault().post(new EventCameraListRefresh());
        finish();
    }


    AlertDialog.Builder dialogPassword;

    private void initInputDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_one_input, null, false);
        EditText etInput = (EditText) dialogView.findViewById(R.id.et_input);
        dialogPassword = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("密码错误，请输入正确密码")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updatePwd = etInput.getText().toString().trim();
                        if (TextUtils.isEmpty(updatePwd)) {
                            finish();
                        } else {
                            params.fid = bean.getFid();
                            params.deviceName = bean.getName();
                            params.deviceType = "password";
                            params.devicePassword = updatePwd;
                            presenter.requestModCamera(params);
                        }
                        if (dialogView.getParent() != null)
                            ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogView.getParent() != null)
                            ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                        dialog.dismiss();
                    }
                });
    }
}
