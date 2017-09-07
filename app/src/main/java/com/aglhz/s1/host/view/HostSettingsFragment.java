package com.aglhz.s1.host.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.entity.bean.GatewaysBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/5/2 0002 20:14.
 * Email: liujia95me@126.com
 */

public class HostSettingsFragment extends BaseFragment {
    public static final String TAG = HostSettingsFragment.class.getSimpleName();
    public static final int UPDATE_HOST_NAME = 1234;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_host_name_host_setting_fragment)
    TextView tvHostName;
    @BindView(R.id.tv_location_host_setting_fragment)
    TextView tvLocation;
    @BindView(R.id.tv_alert_sms_host_setting_fragment)
    TextView tvAlertMessage;
    @BindView(R.id.tv_push_host_setting_fragment)
    TextView tvPush;
    @BindView(R.id.tv_volume_host_setting_fragment)
    TextView tvVolume;
    @BindView(R.id.tv_accredit_host_setting_fragment)
    TextView tvAccredit;
    @BindView(R.id.ll_host_name_host_setting_fragment)
    LinearLayout llHostName;
    private Unbinder unbinder;
    private GatewaysBean.DataBean hostBean;

    public static HostSettingsFragment newInstance(GatewaysBean.DataBean hostBean) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.KEY_HOST, hostBean);
        HostSettingsFragment fragment = new HostSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            hostBean = args.getParcelable(Constants.KEY_HOST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initData() {
        tvHostName.setText(hostBean.getName());
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("主机设置");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_host_name_host_setting_fragment,
            R.id.tv_location_host_setting_fragment,
            R.id.tv_alert_sms_host_setting_fragment,
            R.id.tv_push_host_setting_fragment,
            R.id.tv_volume_host_setting_fragment,
            R.id.tv_accredit_host_setting_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_host_name_host_setting_fragment:
                startForResult(EditHostFragment.newInstance(hostBean), UPDATE_HOST_NAME);
                break;
            case R.id.tv_location_host_setting_fragment:
                start(AddHostFragment.newInstance("", hostBean));
                break;
            case R.id.tv_alert_sms_host_setting_fragment:
                start(AlertSmsFragment.newInstance(hostBean));
                break;
            case R.id.tv_push_host_setting_fragment:
                start(PushSettingsFragment.newInstance(hostBean));
                break;
            case R.id.tv_volume_host_setting_fragment:
                start(VolumeSettingsFragment.newInstance(hostBean));
                break;
            case R.id.tv_accredit_host_setting_fragment:
                start(AuthorizationFragment.newInstance(hostBean));
                break;
        }
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);

        if (data != null) {
            tvHostName.setText(data.getString(Constants.KEY_HOST_NAME));
        }
    }
}
