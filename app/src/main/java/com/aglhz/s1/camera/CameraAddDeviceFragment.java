package com.aglhz.s1.camera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.p2p.core.P2PHandler;
import com.p2p.core.P2PView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ToastUtils;

/**
 * Author: LiuJia on 2017/9/12 0012 11:11.
 * Email: liujia95me@126.com
 */

public class CameraAddDeviceFragment extends BaseFragment {

    private static final String TAG = CameraAddDeviceFragment.class.getSimpleName();

    public static String P2P_ACCEPT = "com.XXX.P2P_ACCEPT";
    public static String P2P_READY = "com.XXX.P2P_READY";
    public static String P2P_REJECT = "com.XXX.P2P_REJECT";

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_input_1)
    EditText etId;
    @BindView(R.id.et_input_2)
    EditText etNickname;
    @BindView(R.id.tv_receive)
    TextView tvReceive;

    @BindView(R.id.et_input_3)
    EditText etPwd;
    Unbinder unbinder;
    private String callID, CallPwd;
    private String userId;

    public static CameraAddDeviceFragment newInstance(String id) {
        CameraAddDeviceFragment fragment = new CameraAddDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        callID = getArguments().getString("id");
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
        toolbarTitle.setText("添加已联网设备");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        if(!TextUtils.isEmpty(callID)){
            etId.setText(callID);
        }
    }

    private void initListener() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        callID = etId.getText().toString().trim();//设备号
        CallPwd = etPwd.getText().toString().trim();
        if(TextUtils.isEmpty(callID)||TextUtils.isEmpty(CallPwd)){
            ToastUtils.showToast(_mActivity,"请输入设备号和密码");
            return;
        }
//        String pwd = P2PHandler.getInstance().EntryPassword(CallPwd);//经过转换后的设备密码
//        P2PHandler.getInstance().call(null, pwd, true, 1, callID, "", "", 2, callID);
    }


//    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(P2P_ACCEPT)) {
//                int[] type = intent.getIntArrayExtra("type");
//                P2PView.type = type[0];
//                P2PView.scale = type[1];
//                tvReceive.append("\n 监控数据接收");
//                Log.e("dxsTest", "监控数据接收:" + callID);
//                P2PHandler.getInstance().openAudioAndStartPlaying(1);//打开音频并准备播放，calllType与call时type一致
//            } else if (intent.getAction().equals(P2P_READY)) {
//                tvReceive.append("\n 监控准备,开始监控");
//                Log.e("dxsTest", "监控准备,开始监控" + callID);
////                pView.sendStartBrod();
//            } else if (intent.getAction().equals(P2P_REJECT)) {
//                int reason_code = intent.getIntExtra("reason_code", -1);
//                int code1 = intent.getIntExtra("exCode1", -1);
//                int code2 = intent.getIntExtra("exCode2", -1);
//                String reject = String.format("\n 监控挂断(reson:%d,code1:%d,code2:%d)", reason_code, code1, code2);
//                tvReceive.append(reject);
//            }
//        }
//    };

}
