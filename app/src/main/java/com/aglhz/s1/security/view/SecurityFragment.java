package com.aglhz.s1.security.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.bean.HostListBean;
import com.aglhz.s1.bean.SecurityBean;
import com.aglhz.s1.data.SecurityData;
import com.aglhz.s1.security.contract.SecurityContract;
import com.aglhz.s1.security.presenter.SecurityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.DensityUtils;


/**
 * Author: LiuJia on 2017/4/26 0026 11:11.
 * Email: liujia95me@126.com
 */

public class SecurityFragment extends BaseFragment<SecurityContract.Presenter> implements SecurityContract.View {
    public static final String TAG = SecurityFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private SecurityRVAdapter adapter;

    public static SecurityFragment newInstance() {
        return new SecurityFragment();
    }

    @NonNull
    @Override
    protected SecurityContract.Presenter createPresenter() {
        return new SecurityPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_security, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
        toolbarTitle.setText("GS-S1智能安防");
    }

    private void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        adapter = new SecurityRVAdapter(SecurityData.getInstance().getAlreadyAddSecuritys());
        adapter.setHeaderView(initHeaderView());
        recyclerView.setAdapter(adapter);
    }

    private View initHeaderView() {
        View headerView = LayoutInflater.from(_mActivity).inflate(R.layout.item_security_header, null);
        TextView tvDescHeader = (TextView) headerView.findViewById(R.id.tv_desc_security_header);
        String title = "离家布防：";
        String desc = "当主机处于离家布防状态时，所有开启的探测器都处于防御状态。";
        tvDescHeader.setText(title + desc);
        Spannable span = new SpannableString(tvDescHeader.getText());
        span.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(_mActivity, 18)), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDescHeader.setText(span);
        return headerView;
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            ALog.e("onItemclick position:" + position + " size:" + adapter.getData().size());
            if (position == adapter.getData().size() - 1) {
                _mActivity.start(AddDetectorFragment.newInstance());
            } else {
                _mActivity.start(DetectorPropertyFragment.newInstance());
            }
            Toast.makeText(_mActivity, "clickItem", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseSecurity(List<SecurityBean> listRecord) {
        //todo:
    }

    @Override
    public void responseHostList(HostListBean hostListBean) {

    }

    @Override
    public void responseChangedHostStatusSuccess(BaseBean baseBean) {

    }
}
