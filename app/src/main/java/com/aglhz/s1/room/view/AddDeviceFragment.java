package com.aglhz.s1.room.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.event.EventDeviceChanged;
import com.aglhz.s1.room.contract.AddDeviceContract;
import com.aglhz.s1.room.presenter.AddDevicePresenter;
import com.bumptech.glide.Glide;
import com.dd.CircularProgressButton;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ToastUtils;

/**
 * Author： Administrator on 2017/5/2 0002.
 * Email： liujia95me@126.com
 */
public class AddDeviceFragment extends BaseFragment<AddDeviceContract.Presenter> implements AddDeviceContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cpb_delete)
    CircularProgressButton cpbDelete;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_room_name)
    TextView tvRoomName;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    Unbinder unbinder;

    private DeviceListBean.DataBean.SubDevicesBean bean;
    private Params params = Params.getInstance();

    public static AddDeviceFragment newInstance(DeviceListBean.DataBean.SubDevicesBean bean,String roomFid) {
        AddDeviceFragment fragment = new AddDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        bundle.putString("roomFid",roomFid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected AddDeviceContract.Presenter createPresenter() {
        return new AddDevicePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_device, container, false);
        bean = (DeviceListBean.DataBean.SubDevicesBean) getArguments().getSerializable("bean");
        params.roomFid = getArguments().getString("roomFid");
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
        toolbarTitle.setText("添加设备");
        toolbarMenu.setText("确定");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        cpbDelete.setVisibility(bean == null ? View.GONE : View.VISIBLE);
        if (bean != null) {
            //把数据赋值
            etName.setText(bean.getName());
            Glide.with(_mActivity)
                    .load(bean.getIcon())
                    .into(ivIcon);
        }
    }

    private void initListener() {
    }

    @OnClick({R.id.cpb_delete, R.id.toolbar_menu, R.id.tv_room_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cpb_delete:
                cpbDelete.setIndeterminateProgressMode(true);
                if (bean == null) {
                    DialogHelper.warningSnackbar(getView(), "删除失败");
                    return;
                }
                cpbDelete.setProgress(50);
                params.index = bean.getIndex();
                mPresenter.requestDelDevice(params);
                break;
            case R.id.toolbar_menu:
                //todo:addparams
                if (bean == null) {
                    mPresenter.requestnewDevice(params);
                } else {
                    params.name = etName.getText().toString().trim();
                    if (TextUtils.isEmpty(params.name)) {
                        ToastUtils.showToast(_mActivity, "名字不能为空");
                        return;
                    }
                    params.index = bean.getIndex();
                    mPresenter.requestModDevice(params);
                }
                break;
            case R.id.tv_room_name:
                mPresenter.requestHouseList(params);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void error(String errorMessage) {
        cpbDelete.setProgress(0);
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseSuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
        EventBus.getDefault().post(new EventDeviceChanged());
        cpbDelete.setProgress(100);
        pop();
    }

    @Override
    public void responseHouseList(List<RoomsBean.DataBean.RoomListBean> data) {
        ALog.e(TAG,"responseHouseList:"+data.size());
        String[] rooms = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            rooms[i] = data.get(i).getName();
        }
        new AlertDialog.Builder(_mActivity)
                .setItems(rooms, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvRoomName.setText(data.get(which).getName());
                        params.roomId = data.get(which).getIndex();
                    }
                }).show();
    }
}
