package com.aglhz.s1.more.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.RoomTypesBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.more.contract.RoomManagerContract;
import com.aglhz.s1.more.presenter.RoomManagerPresenter;
import com.aglhz.s1.widget.PtrHTFrameLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ToastUtils;
import cn.itsite.multiselector.MultiSelectorDialog;

/**
 * Author: LiuJia on 2017/5/2 0002 20:17.
 * Email: liujia95me@126.com
 */

public class RoomManagerFragment extends BaseFragment<RoomManagerContract.Presenter> implements RoomManagerContract.View {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrHTFrameLayout;

    private Unbinder unbinder;
    private RoomManagerRVAdapter adapter;
    private Params params = Params.getInstance();
    private MultiSelectorDialog switchRoomDialog;
    RoomsBean.DataBean.RoomListBean addIconBean = new RoomsBean.DataBean.RoomListBean();
    private List<RoomTypesBean.DataBean> roomTypeDatas;

    public static RoomManagerFragment newInstance() {
        return new RoomManagerFragment();
    }

    @NonNull
    @Override
    protected RoomManagerContract.Presenter createPresenter() {
        return new RoomManagerPresenter(this);
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
        initPtrFrameLayout(ptrHTFrameLayout,recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("房间管理");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        switchRoomDialog = MultiSelectorDialog.builder(_mActivity)
                .setTitle("添加房间")
                .setTabVisible(false)
                .setOnItemClickListener((pagerPosition, optionPosition, option) -> {
                    RoomTypesBean.DataBean bean = roomTypeDatas.get(optionPosition);
                    params.roomName = bean.getName();
                    params.roomTypeFid = bean.getFid();
                    params.residenceFid = "irifirkfk";//todo:待改
                    ALog.e(TAG,"name:"+params.roomName);
                    ALog.e(TAG,"roomTypeFid:"+params.roomTypeFid);
                    mPresenter.requestAddHouse(params);
                    switchRoomDialog.dismiss();
                })
                .build();

        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 3));

        List<RoomsBean.DataBean.RoomListBean> datas = new ArrayList<>();

        addIconBean.setName("添加房间");
        datas.add(addIconBean);
        adapter = new RoomManagerRVAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setNewData(datas);
        params.page = 1;
        params.pageSize = 10;
        mPresenter.requestHouseList(params);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestHouseList(params);
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == adapter.getData().size() - 1) {
                    showLoading();
                    mPresenter.requestRoomTypeList(params);
                } else {
//                    _mActivity.start(DetectorPropertyFragment.newInstance());

                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void error(String errorMessage) {
        dismissLoading();
        DialogHelper.errorSnackbar(getView(), errorMessage);
        ptrHTFrameLayout.refreshComplete();
    }

    @Override
    public void responseHouseList(List<RoomsBean.DataBean.RoomListBean> data) {
        data.add(addIconBean);
        adapter.setNewData(data);
        ptrHTFrameLayout.refreshComplete();
    }

    @Override
    public void responseAddHouse(BaseBean bean) {
        dismissLoading();
        ToastUtils.showToast(_mActivity, "添加成功");
        mPresenter.requestHouseList(params);
    }

    @Override
    public void responseRoomTypeList(List<RoomTypesBean.DataBean> data) {
        switchRoomDialog.show();
        dismissLoading();
        roomTypeDatas = data;
        List<String> strList = new ArrayList<>();
        for (RoomTypesBean.DataBean bean : data) {
            strList.add(bean.getName());
        }
        getView().post(() -> switchRoomDialog.notifyDataSetChanged(strList));
    }
}
