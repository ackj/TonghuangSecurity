package com.aglhz.s1.camera;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.camera.contract.CameraSettingContract;
import com.aglhz.s1.camera.presenter.CameraSettingPresenter;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.CameraBean;
import com.aglhz.s1.event.EventCameraPwdChanged;
import com.p2p.core.P2PHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/9/14 0014 17:07.
 * Email: liujia95me@126.com
 */

public class CameraSettingFragment extends BaseFragment<CameraSettingContract.Presenter> implements CameraSettingContract.View{

    private static final String TAG = CameraSettingFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_password)
    TextView tvPassword;

    Unbinder unbinder;
    private CameraBean.DataBean bean;
    private AlertDialog.Builder dialogBuilder;
    private EditText etInput;
    private boolean isNickname;
    private Params params = Params.getInstance();

    @NonNull
    @Override
    protected CameraSettingContract.Presenter createPresenter() {
        return new CameraSettingPresenter(this);
    }

    public static CameraSettingFragment newInstance(CameraBean.DataBean bean) {
        CameraSettingFragment fragment = new CameraSettingFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        bean = (CameraBean.DataBean) getArguments().getSerializable("bean");
        EventBus.getDefault().register(this);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initInputDialog();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("设置");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        if (bean != null) {
            tvNickname.setText(bean.getName());
            tvPassword.setText(bean.getPassword());
        }
    }

    private void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.ll_pwd, R.id.ll_video, R.id.ll_nickname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_nickname:
                isNickname = true;
                etInput.setText(bean.getName());
                etInput.setSelection(bean.getName().length());
                dialogBuilder.show();
                break;
            case R.id.ll_pwd:
                isNickname = false;
                etInput.setText(bean.getPassword());
                etInput.setSelection(bean.getPassword().length());
                dialogBuilder.show();
                break;
            case R.id.ll_video:
                _mActivity.start(CameraFileRecordFragment.newInstance(bean.getNo(),bean.getPassword()));
                break;

        }
    }

    private void initInputDialog() {
        View dialogView = LayoutInflater.from(_mActivity).inflate(R.layout.dialog_one_input, null, false);
        etInput = (EditText) dialogView.findViewById(R.id.et_input);
        dialogBuilder = new AlertDialog.Builder(_mActivity)
                .setView(dialogView)
                .setTitle("修改")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = etInput.getText().toString().trim();
                        showLoading();
                        if(TextUtils.isEmpty(result)){
                            DialogHelper.warningSnackbar(getView(),"请输入内容");
                        }else{
                            params.fid = bean.getFid();
                            if(isNickname){
                                params.deviceName = result;
                                params.deviceType = "name";
                                params.devicePassword = "";
                                mPresenter.requestModCamera(params);
                            }else{
                                updatePassword(result);
                            }
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

    private void updatePassword(String pwd){
        params.deviceType = "password";
        params.deviceName = "";
        params.devicePassword = pwd;

        P2PHandler.getInstance().setDevicePassword(bean.getNo(),
                P2PHandler.getInstance().EntryPassword(bean.getPassword()),
                P2PHandler.getInstance().EntryPassword(pwd),
                pwd,pwd);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCameraPwdChanged event){
        if(event.result == 0){
            mPresenter.requestModCamera(params);
        }else{
            dismissLoading();
            DialogHelper.errorSnackbar(getView(),"修改密码失败");
        }
    }

    @Override
    public void responseSuccess(BaseBean baseBean) {
        dismissLoading();
        if(isNickname){
            bean.setName(params.deviceName);
            tvNickname.setText(params.deviceName);
        }else{
            bean.setPassword(params.devicePassword);
            tvPassword.setText(params.devicePassword);
        }
        DialogHelper.successSnackbar(getView(),"修改成功");
    }
}
