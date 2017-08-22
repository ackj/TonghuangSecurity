package com.aglhz.s1.security.view;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.DefenseLineLevel;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.SecurityBean;
import com.aglhz.s1.entity.bean.SubDeviceDetBean;
import com.aglhz.s1.event.EventRefreshSecurity;
import com.aglhz.s1.security.contract.DetectorPropertyContract;
import com.aglhz.s1.security.presenter.DetectorPropertyPresenter;
import com.bumptech.glide.Glide;
import com.dd.CircularProgressButton;
import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;


/**
 * Author: 2017/5/2 0002.
 * Email:liujia95me@126.com
 */
public class DetectorPropertyFragment extends BaseFragment<DetectorPropertyContract.Presenter> implements DetectorPropertyContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cpb_delete_fragment_detector_property)
    CircularProgressButton cpbDelete;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_line_of_defense)
    TextView tvLineOfDefense;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.sb_detection_door_window)
    SwitchButton sbDetectionDoorWindow;
    @BindView(R.id.sb_alarm_delay)
    SwitchButton sbAlarmDelay;

    private String[] lineOfDefenseArr = {"第一防线", "第二防线", "24小时防线"};
    private String defenseLevel = DefenseLineLevel.DLL_FIRST;
    Params params = Params.getInstance();
    Unbinder unbinder;
    private SecurityBean.DataBean.SubDevicesBean deviceBean;

    public static DetectorPropertyFragment newInstance(SecurityBean.DataBean.SubDevicesBean bean) {
        DetectorPropertyFragment fragment = new DetectorPropertyFragment();
        Bundle args = new Bundle();
        args.putSerializable("bean", bean);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    protected DetectorPropertyContract.Presenter createPresenter() {
        return new DetectorPropertyPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detector_property, container, false);
        deviceBean = (SecurityBean.DataBean.SubDevicesBean) getArguments().getSerializable("bean");
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("探测器属性");
        toolbarMenu.setText("确定");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        if (deviceBean != null) {
            params.category = deviceBean.getCategory();
            params.index = deviceBean.getIndex();
            mPresenter.requestSubDeviceDet(params);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cpb_delete_fragment_detector_property, R.id.toolbar_menu,R.id.ll_defenseLevel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cpb_delete_fragment_detector_property:
                cpbDelete.setIndeterminateProgressMode(true);
                if (deviceBean == null) {
                    DialogHelper.warningSnackbar(getView(), "删除失败");
                    return;
                }
                params.index = deviceBean.getIndex();
                cpbDelete.setProgress(50);
                mPresenter.requestDelsensor(params);
                break;
            case R.id.toolbar_menu:
                params.index = deviceBean.getIndex();
                params.name = etName.getText().toString().trim();
                if (TextUtils.isEmpty(params.name)) {
                    DialogHelper.warningSnackbar(getView(), "名称不能为空");
                    return;
                }
                params.defenseLevel = defenseLevel;
                mPresenter.requestModsensor(params);
                break;
            case R.id.ll_defenseLevel:
                new AlertDialog.Builder(_mActivity)
                        .setItems(lineOfDefenseArr, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        defenseLevel = DefenseLineLevel.DLL_FIRST;
                                        break;
                                    case 1:
                                        defenseLevel = DefenseLineLevel.DLL_SECOND;
                                        break;
                                    case 2:
                                        defenseLevel = DefenseLineLevel.DLL_24HOUR;
                                        break;
                                }
                                tvLineOfDefense.setText(getLineOfDefenseStr(defenseLevel));
                            }
                        }).show();
                break;
        }

    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        cpbDelete.setProgress(0);
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseDetectorProperty(BaseBean baseBean) {

    }

    @Override
    public void responseNodifSuccess(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), "修改成功");
    }

    @Override
    public void responseDelSuccess(BaseBean baseBean) {
        cpbDelete.setProgress(100);
        EventBus.getDefault().post(new EventRefreshSecurity(null));
        DialogHelper.successSnackbar(getView(), "删除成功");
        pop();
    }

    @Override
    public void responseSubDeviceDet(SubDeviceDetBean bean) {
        tvLineOfDefense.setText(getLineOfDefenseStr(bean.getData().getDefenseLevel()));
        defenseLevel = bean.getData().getDefenseLevel();
        etName.setText(bean.getData().getName());
        Glide.with(_mActivity)
                .load(bean.getData().getIcon())
                .error(R.mipmap.ic_logo)
                .into(ivIcon);
        sbAlarmDelay.setChecked(bean.getData().getAlarmDelay() == 1);
    }

    @Override
    public void responseModsensor(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), "修改成功");
        pop();
    }

    private String getLineOfDefenseStr(String english) {
        switch (english) {
            case DefenseLineLevel.DLL_SECOND:
                return "第二防线";
            case DefenseLineLevel.DLL_FIRST:
                return "第一防线";
            case DefenseLineLevel.DLL_24HOUR:
                return "24小时防线";
            default:
                return "";
        }
    }
}
