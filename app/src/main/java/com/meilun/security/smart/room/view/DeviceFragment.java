package com.meilun.security.smart.room.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meilun.security.smart.R;
import com.meilun.security.smart.common.Params;
import com.meilun.security.smart.entity.bean.DevicesBean;
import com.meilun.security.smart.security.view.AddDetectorRVAdapter;
import com.meilun.security.smart.widget.PtrHTFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.adialog.dialogfragment.BaseDialogFragment;
import cn.itsite.statemanager.StateManager;

/**
 * Author: LiuJia on 2017/12/26 0026 09:22.
 * Email: liujia95me@126.com
 */

public class DeviceFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;

    private StateManager mStateManager;

    Unbinder unbinder;

    private AddDetectorRVAdapter adapter;
    private Params params = Params.getInstance();
    private BaseDialogFragment dialogAddCamera;

    public static DeviceTypeFragment newInstance(String roomFid) {
        DeviceTypeFragment fragment = new DeviceTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fid", roomFid);
        fragment.setArguments(bundle);
        return fragment;
    }

//    @NonNull
//    @Override
//    protected DeviceTypeContract.Presenter createPresenter() {
//        return new DeviceTypePresenter(this);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        params.roomFid = getArguments().getString("fid");
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initStateManager();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        params.page = 1;
//        mPresenter.requestDeviceType(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("添加设备");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        adapter = new AddDetectorRVAdapter();
        recyclerView.setAdapter(adapter);

//        mPresenter.requestDeviceType(params);
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyText("暂无历史记录！")
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setConvertListener((holder, stateLayout) ->
                        holder.setOnClickListener(R.id.bt_empty_state,
                                v -> ptrFrameLayout.autoRefresh())
                                .setText(R.id.bt_empty_state, "点击刷新"))
                .build();
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            DevicesBean.DataBean.DeviceTypeListBean bean = adapter.getItem(position);
            params.deviceType = bean.getCode();
            params.name = bean.getName();
            if ("camera01".equals(bean.getCode())) {
                //弹框选择
//                showSelectedDialog();
            } else {
//                mPresenter.requestAddDevice(params);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
