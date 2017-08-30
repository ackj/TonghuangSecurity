package com.aglhz.s1.host.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/5/2 0002 20:14.
 * Email: liujia95me@126.com
 */

public class PushSettingsFragment extends BaseFragment {
    public static final String TAG = PushSettingsFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sb_waijiedianyuandiaodian)
    SwitchButton sbWaijiedianyuandiaodian;
    @BindView(R.id.sb_huifuwaijiedianyuan)
    SwitchButton sbHuifuwaijiedianyuan;
    @BindView(R.id.sb_bufangchefang)
    SwitchButton sbBufangchefang;
    @BindView(R.id.sb_zhujidianchidianliangdi)
    SwitchButton sbZhujidianchidianliangdi;
    @BindView(R.id.sb_chuanganqidianliangdi)
    SwitchButton sbChuanganqidianliangdi;
    @BindView(R.id.sb_wifilianjei)
    SwitchButton sbWifilianjei;
    @BindView(R.id.sb_wifiduankai)
    SwitchButton sbWifiduankai;
    @BindView(R.id.sb_duanxintuisong)
    SwitchButton sbDuanxintuisong;
    private Unbinder unbinder;
    private GatewaysBean.DataBean hostBean;

    public static PushSettingsFragment newInstance(GatewaysBean.DataBean hostBean) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.KEY_HOST, hostBean);
        PushSettingsFragment fragment = new PushSettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_push_settings, container, false);
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
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("推送设置");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
