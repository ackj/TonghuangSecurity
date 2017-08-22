package com.aglhz.s1.gateway.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.gateway.contract.GatewayListContract;
import com.aglhz.s1.gateway.presenter.GatewayListPresenter;
import com.aglhz.s1.widget.PtrHTFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.statemanager.StateLayout;

public class GatewayListFragment extends BaseFragment<GatewayListContract.Presenter> implements GatewayListContract.View {

    public static final String TAG = GatewayListFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.stateLayout)
    StateLayout stateLayout;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;
    private GatewayListRVAdapter adapter;

    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static GatewayListFragment newInstance() {
        return new GatewayListFragment();
    }

    @NonNull
    @Override
    protected GatewayListContract.Presenter createPresenter() {
        return new GatewayListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("管理主机");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new GatewayListRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            //todo:mPresenter.request
        }, recyclerView);
        recyclerView.setAdapter(adapter);

        params.page = 1;
        params.pageSize = 10;
        mPresenter.requestGateways(params);
    }

    private void initListener() {

    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = 10;
        mPresenter.requestGateways(params);
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
        ptrFrameLayout.refreshComplete();
        adapter.loadMoreComplete();
    }

    @Override
    public void responseGateways(List<GatewaysBean.DataBean> data) {
        adapter.setNewData(data);
        if (data.size() < params.pageSize) {
            ALog.e(TAG,"responseGateways:end");
            adapter.loadMoreEnd(true);
        } else {
            ALog.e(TAG,"responseGateways:more");
            adapter.loadMoreComplete();
        }
        ptrFrameLayout.refreshComplete();
    }
}