package com.aglhz.s1.room.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.aglhz.s1.event.EventDeviceChanged;
import com.aglhz.s1.room.contract.AddDeviceContract;
import com.aglhz.s1.room.presenter.AddDevicePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
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
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.et_name)
    EditText etName;

    Unbinder unbinder;

    private DeviceListBean.DataBean.SubDevicesBean bean;
    private Params params = Params.getInstance();

    public static AddDeviceFragment newInstance(DeviceListBean.DataBean.SubDevicesBean bean) {
        AddDeviceFragment fragment = new AddDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
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
        btnDelete.setVisibility(bean == null ? View.GONE : View.VISIBLE);
        if (bean != null) {
            //把数据赋值
            etName.setText(bean.getName());
        }
    }

    private void initListener() {
    }

    @OnClick({R.id.btn_delete, R.id.toolbar_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
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
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void responseSuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
        EventBus.getDefault().post(new EventDeviceChanged());
        pop();
    }
}
