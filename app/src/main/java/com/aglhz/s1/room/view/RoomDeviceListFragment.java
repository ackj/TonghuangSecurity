package com.aglhz.s1.room.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.event.EventAddDevice;
import com.aglhz.s1.event.EventDeviceChanged;
import com.aglhz.s1.event.EventSelectedDeviceType;
import com.aglhz.s1.event.EventSwitchHost;
import com.aglhz.s1.room.contract.RoomDeviceListContract;
import com.aglhz.s1.room.presenter.RoomDeviceListPresenter;
import com.aglhz.s1.widget.PtrHTFrameLayout;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
import cn.itsite.abase.utils.ToastUtils;
import cn.itsite.multiselector.MultiSelectorDialog;
import cn.itsite.statemanager.StateManager;

/**
 * Author： Administrator on 2017/8/18 0018.
 * Email： liujia95me@126.com
 */
public class RoomDeviceListFragment extends BaseFragment<RoomDeviceListContract.Presenter> implements RoomDeviceListContract.View {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;
    Unbinder unbinder;
    private BaseRecyclerViewAdapter<String, BaseViewHolder> selectorAdapter;
    private List<String> roomList = new ArrayList<>();
    private ImageView ivHeader;
    private Params params = Params.getInstance();
    private RoomDeviceList2RVAdapter adapter;
    private MultiSelectorDialog switchRoomDialog;
    private List<RoomsBean.DataBean.RoomListBean> roomListBean;
    private RoomsBean.DataBean.RoomListBean selectRoom;
    private boolean isFirst = true;//是否是第一次进来
    private StateManager mStateManager;

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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initStateManager();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    @Override
    public void onRefresh() {
        params.category = Constants.DEVICE_CTRL;
        mPresenter.requestDeviceList(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("大厅");
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_homemore_90px));
        toolbar.inflateMenu(R.menu.room_menu);

        //通过反射强制每一个Menu的Item同时显示图标和文字。
        Menu menu = toolbar.getMenu();
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.add_device:
                    ToastUtils.showToast(App.mContext, "添加设备");
                    if (selectRoom == null) {
                        ToastUtils.showToast(_mActivity, "请选择房间");
                        return true;
                    }
//                    mPresenter.requestAddDevice(params);
                    _mActivity.start(DeviceTypeFragment.newInstance(selectRoom.getFid()));
                    break;
                case R.id.change_room:
                    mPresenter.requestHouseList(params);
                    break;
            }
            return true;
        });
    }

    private void initData() {
        switchRoomDialog = MultiSelectorDialog.builder(_mActivity)
                .setTitle("切换房间")
                .setTabVisible(false)
                .setOnItemClickListener((pagerPosition, optionPosition, option) -> {
                    selectRoom(roomListBean.get(optionPosition));
                    switchRoomDialog.hide();
                })
                .build();

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new RoomDeviceList2RVAdapter();
        ivHeader = new ImageView(_mActivity);
        Glide.with(_mActivity)
                .load(R.drawable.room_cesuo_1242px_745px)
                .into(ivHeader);
        adapter.setHeaderView(ivHeader);
        recyclerView.setAdapter(adapter);

        mPresenter.requestHouseList(params);

        selectorAdapter = new BaseRecyclerViewAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(android.R.id.text1, item);
            }
        };
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyText("该房间暂无设备，空空如也！")
                .setEmptyImage(R.drawable.ic_no_device_empty_state_300)
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setConvertListener((holder, stateLayout) ->
                        holder.setOnClickListener(R.id.bt_empty_state,
                                v -> ptrFrameLayout.autoRefresh())
                                .setText(R.id.bt_empty_state, "点击刷新"))
                .build();
    }

    private void selectRoom(RoomsBean.DataBean.RoomListBean bean) {
        selectRoom = bean;
        ALog.e(TAG, "selectRoom--->:" + selectRoom);
        params.roomId = selectRoom.getIndex();
        params.category = Constants.DEVICE_CTRL;
        //请求设备
        mPresenter.requestDeviceList(params);
        int resId = R.drawable.room_room_1242px_745px;
        switch (bean.getName()) {
            case "大厅":
                resId = R.drawable.room_dating_1242px_745px;
                break;
            case "厨房":
                resId = R.drawable.room_chufang_1242px_745px;
                break;
            case "卧室":
                resId = R.drawable.room_room_1242px_745px;
                break;
            case "厕所":
                resId = R.drawable.room_cesuo_1242px_745px;
                break;
        }
        toolbarTitle.setText(bean.getName());
        Glide.with(_mActivity)
                .load(resId)
                .into(ivHeader);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAddDevice(EventAddDevice event) {
        _mActivity.start(AddDeviceFragment.newInstance(null, selectRoom));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectedDeviceType(EventSelectedDeviceType event) {
        new AlertDialog.Builder(_mActivity)
                .setTitle("学习中...")
                .setMessage("设备是否收到了正确的反馈？")
                .setNegativeButton("否", null)
                .setPositiveButton("是", (dialog, which) -> {
                    params.status = 1;
                    mPresenter.requestNewDeviceConfirm(params);
                }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeDataEvent(EventDeviceChanged event) {
        onRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchHost(EventSwitchHost event) {
        onRefresh();
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            DeviceListBean.DataBean.SubDevicesBean bean = adapter.getItem(position);
            switch (view.getId()) {
                case R.id.iv_setting:
                    if (selectRoom == null) {
                        DialogHelper.warningSnackbar(getView(), "请选择房间");
                        return;
                    }
                    _mActivity.start(AddDeviceFragment.newInstance(bean, selectRoom));
                    break;
                default:
                    _mActivity.start(DeviceOnOffFragment.newInstance(bean.getName(), bean.getExtInfo().getNode(), bean.getIndex()));
                    break;
            }
        });
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void responseDeviceList(List<DeviceListBean.DataBean.SubDevicesBean> data) {
        ptrFrameLayout.refreshComplete();
        if (data.size() < Constants.PAGE_SIZE) {
            //如果加载数量小于个数，直接完成
            adapter.loadMoreEnd();
        } else {
            //否则，可继续加载
            adapter.loadMoreComplete();
        }
        if (params.page == 1) {
            adapter.setNewData(data);
        } else {
            adapter.addData(data);
        }
        //如果个数为0，显示空
        if (adapter.getData().size() == 0) {
            mStateManager.showEmpty();
            adapter.loadMoreEnd();
        } else {
            mStateManager.showContent();
        }
    }

    @Override
    public void responseHouseList(List<RoomsBean.DataBean.RoomListBean> data) {
        ALog.e(TAG, "responseHouseList:" + data.size());
        if (isFirst) {
            selectRoom(data.get(0));//
            isFirst = false;
        } else {
            switchRoomDialog.show();
            dismissLoading();
            roomListBean = data;
            List<String> strList = new ArrayList<>();
            for (RoomsBean.DataBean.RoomListBean bean : data) {
                strList.add(bean.getName());
            }
            getView().post(() -> switchRoomDialog.notifyDataSetChanged(strList));
        }
    }

    @Override
    public void error(String errorMessage) {
        super.error(errorMessage);
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void responseDevicectrl(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
    }

    @Override
    public void responseNewDeviceConfirm(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
        onRefresh();
    }
}
