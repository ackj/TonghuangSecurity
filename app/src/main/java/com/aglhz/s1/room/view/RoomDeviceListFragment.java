package com.aglhz.s1.room.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.room.contract.RoomDeviceListContract;
import com.aglhz.s1.room.presenter.RoomDeviceListPresenter;
import com.aglhz.s1.widget.PtrHTFrameLayout;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
import cn.itsite.multiselector.MultiSelectorDialog;

/**
 * Author： Administrator on 2017/8/18 0018.
 * Email： liujia95me@126.com
 */
public class RoomDeviceListFragment extends BaseFragment<RoomDeviceListContract.Presenter> implements RoomDeviceListContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_menu)
    ImageView ivMenu;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrHTFrameLayout;

    Unbinder unbinder;

    private BaseRecyclerViewAdapter<String, BaseViewHolder> selectorAdapter;
    private List<String> roomList = new ArrayList<>();
    private ImageView ivHeader;
    private Params params = Params.getInstance();
    private RoomDeviceList2RVAdapter adapter;
    private MultiSelectorDialog switchRoomDialog;
    private List<RoomsBean.DataBean.RoomListBean> roomListBean;

    public static RoomDeviceListFragment newInstance() {
        return new RoomDeviceListFragment();
    }

    @NonNull
    @Override
    protected RoomDeviceListContract.Presenter createPresenter() {
        return new RoomDeviceListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout(ptrHTFrameLayout, recyclerview);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestDeviceList(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("大厅");
    }

    private void initData() {
        switchRoomDialog = MultiSelectorDialog.builder(_mActivity)
                .setTitle("切换房间")
                .setTabVisible(false)
                .setOnItemClickListener((pagerPosition, optionPosition, option) -> {
                    RoomsBean.DataBean.RoomListBean bean = this.roomListBean.get(optionPosition);
                    params.roomName = bean.getName();
                    params.roomTypeFid = bean.getFid();
                    //todo:切换房间接口待定
                })
                .build();

        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));

        mPresenter.requestDeviceList(params);
        adapter = new RoomDeviceList2RVAdapter();
        ivHeader = new ImageView(_mActivity);
        Glide.with(_mActivity)
                .load(R.drawable.room_cesuo_1242px_745px)
                .into(ivHeader);
        adapter.setHeaderView(ivHeader);
        recyclerview.setAdapter(adapter);

        selectorAdapter = new BaseRecyclerViewAdapter<String,
                BaseViewHolder>(android.R.layout.simple_list_item_1) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(android.R.id.text1, item);
            }
        };
    }

    private void initListener() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                DeviceListBean.DataBean.SubDevicesBean bean = adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.iv_setting:
                        _mActivity.start(AddDeviceFragment.newInstance(bean));
                        break;
                }
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @OnClick({R.id.toolbar_menu})
    public void onViewClicked(View view) {
        PopupMenu popupMenu = new PopupMenu(_mActivity, view);
        popupMenu.inflate(R.menu.room_menu);
        //将指定的菜单布局进行加载
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_device:
                        mPresenter.requestAddDevice(params);
                        break;
                    case R.id.change_room:
                        //todo:待改
                        break;
                }
                return false;
            }
        });//给菜单绑定监听
        //展示菜单
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
            mHelper.setForceShowIcon(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        popupMenu.show();
    }

    private void changedRoom(String room) {
        toolbarTitle.setText(room);
        Glide.with(_mActivity)
                .load(R.drawable.room_dating_1242px_745px)
                .into(ivHeader);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void responseDeviceList(List<DeviceListBean.DataBean.SubDevicesBean> data) {
        adapter.setNewData(data);
        ptrHTFrameLayout.refreshComplete();
    }

    @Override
    public void responseHouseList(List<RoomsBean.DataBean.RoomListBean> data) {
        switchRoomDialog.show();
        dismissLoading();
        roomListBean = data;
        List<String> strList = new ArrayList<>();
        for (RoomsBean.DataBean.RoomListBean bean : data) {
            strList.add(bean.getName());
        }
        getView().post(() -> switchRoomDialog.notifyDataSetChanged(strList));
    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.errorSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseAddDevice(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
    }
}
