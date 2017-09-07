package com.aglhz.s1.host.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.entity.bean.HostSettingsBean;
import com.aglhz.s1.host.contract.HostSettingsContract;
import com.aglhz.s1.host.presenter.HostSettingsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.common.RxManager;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.KeyBoardUtils;

/**
 * Author: LiuJia on 2017/5/2 0002 20:14.
 * Email: liujia95me@126.com
 */

public class AlertSmsFragment extends BaseFragment<HostSettingsContract.Presenter> implements HostSettingsContract.View {
    public static final String TAG = AlertSmsFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_phone1_alert_sms_fragment)
    EditText etPhone1;
    @BindView(R.id.et_phone2_alert_sms_fragment)
    EditText etPhone2;
    private Unbinder unbinder;
    private GatewaysBean.DataBean hostBean;
    private Params params = Params.getInstance();

    public static AlertSmsFragment newInstance(GatewaysBean.DataBean hostBean) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.KEY_HOST, hostBean);
        AlertSmsFragment fragment = new AlertSmsFragment();
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

    @NonNull
    @Override
    protected HostSettingsContract.Presenter createPresenter() {
        return new HostSettingsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert_sms, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("报警短信");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
        toolbarMenu.setText("保存");
        toolbarMenu.setOnClickListener(v -> {
            params.gateway = hostBean.getFid();
            params.type = Constants.PHONE;
            params.subType = Constants.P_PUSH;
            params.val = etPhone1.getText().toString().trim() + "," + etPhone2.getText().toString().trim();
            mPresenter.requestSetHost(params);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyBoardUtils.hideKeybord(etPhone1, App.mContext);//必须在unbind之前调用。
        unbinder.unbind();
    }

    @Override
    public void responseSetHost(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), baseBean.getOther().getMessage());
        pop();
    }

    @Override
    public void responseHostSettings(HostSettingsBean baseBean) {

    }
}
