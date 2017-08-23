package com.aglhz.s1.history.view;

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
import com.aglhz.s1.entity.bean.DeviceLogBean;
import com.aglhz.s1.history.DeviceLogsRVAdapter;
import com.aglhz.s1.history.contract.DeviceLogsContract;
import com.aglhz.s1.history.presenter.DeviceLogsPresenter;
import com.aglhz.s1.widget.PtrHTFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 16:13.
 * Email: liujia95me@126.com
 */
public class DeviceLogsFragment extends BaseFragment<DeviceLogsContract.Presenter> implements DeviceLogsContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;

    Unbinder unbinder;
    private DeviceLogsRVAdapter adapter;
    private Params params = Params.getInstance();


    public static DeviceLogsFragment newInstance() {
        return new DeviceLogsFragment();
    }

    @NonNull
    @Override
    protected DeviceLogsContract.Presenter createPresenter() {
        return new DeviceLogsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    @Override
    public void onRefresh() {
        params.pageSize = 10;
        params.page = 1;
        mPresenter.requestDeviceLogs(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("历史记录");
    }

    private void initData() {
        adapter = new DeviceLogsRVAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestDeviceLogs(params);
        }, recyclerView);
        recyclerView.setAdapter(adapter);

        onRefresh();
    }

    private void initListener() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
        ptrFrameLayout.refreshComplete();
        adapter.loadMoreComplete();
    }

    @Override
    public void responseDeviceLogs(List<DeviceLogBean.DataBean.LogsBean> data) {
        if(params.page==1){
            adapter.setNewData(data);
        }else{
            adapter.addData(data);
        }
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
