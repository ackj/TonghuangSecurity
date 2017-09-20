package com.aglhz.s1.camera;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.camera.contract.CameraListContract;
import com.aglhz.s1.camera.presenter.CameraListPresenter;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.CameraBean;
import com.aglhz.s1.event.EventCameraListRefresh;
import com.aglhz.s1.widget.PtrHTFrameLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.statemanager.StateManager;

/**
 * Author: LiuJia on 2017/9/12 0012 09:09.
 * Email: liujia95me@126.com
 */

public class CameraListFragment extends BaseFragment<CameraListContract.Presenter> implements CameraListContract.View {

    private static final String TAG = CameraListFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;

    Unbinder unbinder;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    private StateManager mStateManager;
    private String[] addSelectedArr = {"新设备配置网络", "添加已联网设备"};
    private Params params = Params.getInstance();
    private CameraListRVAdapter adapter;
    private CameraBean.DataBean addCameraBean;
    private List<CameraBean.DataBean> cameraList;
    private EditText etDeviceId;
    private EditText etNickname;
    private EditText etPassword;
    private AlertDialog.Builder dialogAddCamera;

    public static CameraListFragment newInstance() {
        CameraListFragment fragment = new CameraListFragment();
        return fragment;
    }

    @NonNull
    @Override
    protected CameraListContract.Presenter createPresenter() {
        return new CameraListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initAddCameraDialog();
        initStateManager();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("智能监控");
        toolbarMenu.setText("设置");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new CameraListRVAdapter();
        recyclerView.setAdapter(adapter);

        addCameraBean = new CameraBean.DataBean();
        addCameraBean.setName("添加监控");

        mPresenter.requestCameraList(params);
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = Constants.PAGE_SIZE;
        //要清空所选中的那些设备。
        mPresenter.requestCameraList(params);
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyImage(R.drawable.bg_vidicon_360px)
                .setEmptyText("还没有添加摄像头哦,赶紧去添加吧~")
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> showSelectedDialog())
                .setConvertListener((holder, stateLayout) ->
                        holder.setOnClickListener(R.id.bt_empty_state,
                                v -> showSelectedDialog())
                                .setText(R.id.bt_empty_state, "马上添加"))
                .build();
    }

    private void showSelectedDialog() {
        new AlertDialog.Builder(_mActivity)
                .setItems(addSelectedArr, (dialog, which) -> {
                    if (which == 0) {
                        _mActivity.start(CameraWifiInputFragment.newInstance());
                    } else {
                        dialogAddCamera.show();
                    }
                })
                .show();
    }

    private void initAddCameraDialog() {
        View dialogView = LayoutInflater.from(_mActivity).inflate(R.layout.fragment_input_video, null);
        etDeviceId = (EditText) dialogView.findViewById(R.id.et_input_1);
        etNickname = (EditText) dialogView.findViewById(R.id.et_input_2);
        etPassword = (EditText) dialogView.findViewById(R.id.et_input_3);

        dialogAddCamera = new AlertDialog.Builder(_mActivity)
                .setView(dialogView)
                .setCancelable(false)
                .setTitle("添加设备")
                .setPositiveButton("确定", (dialog, which) -> {
                    params.deviceId = etDeviceId.getText().toString().trim();
                    params.deviceName = etNickname.getText().toString().trim();
                    params.devicePassword = etPassword.getText().toString().trim();
                    if (dialogView.getParent() != null)
                        ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                    dialog.dismiss();
                    if (TextUtils.isEmpty(params.deviceId)) {
                        DialogHelper.warningSnackbar(getView(), "请输入摄像头ID");
                        return;
                    }
                    if (TextUtils.isEmpty(params.deviceName)) {
                        DialogHelper.warningSnackbar(getView(), "请输入摄像头昵称");
                        return;
                    }
                    if (TextUtils.isEmpty(params.devicePassword)) {
                        DialogHelper.warningSnackbar(getView(), "请输入摄像头密码");
                        return;
                    }
                    mPresenter.requestNewCamera(params);
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    if (dialogView.getParent() != null)
                        ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                    dialog.dismiss();
                });
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                CameraBean.DataBean bean = adapter.getItem(position);
                if (position == adapter.getData().size() - 1) {
                    showSelectedDialog();
                } else {
                    if (cameraList == null || cameraList.size() == 0) {
                        return;
                    }
                    Intent intent = new Intent(_mActivity, CameraPlayActivity.class);
                    intent.putExtra("bean",bean);
                    _mActivity.startActivity(intent);
                }
            }
        });


        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter1, View view, int position) {
                CameraBean.DataBean bean = adapter.getItem(position);
                if (position != adapter.getData().size() - 1) {
                    new AlertDialog.Builder(_mActivity)
                            .setMessage("确认删除？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    params.fid = bean.getFid();
                                    mPresenter.requestDelCamera(params);
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }
                return false;
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCameraListRefresh event){
        onRefresh();
    }

    @Override
    public void error(String errorMessage) {
        super.error(errorMessage);
        ptrFrameLayout.refreshComplete();
        if (params.page == 1) {
            mStateManager.showError();
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
    }

    @Override
    public void responseCameraList(List<CameraBean.DataBean> data) {
        ptrFrameLayout.refreshComplete();
        data.add(addCameraBean);
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
            cameraList = data;
            mStateManager.showContent();
        }
    }

    @Override
    public void responseSuccess(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), baseBean.getOther().getMessage());
        onRefresh();
    }

    @OnClick(R.id.toolbar_menu)
    public void onViewClicked() {
        List<CameraBean.DataBean> data = adapter.getData();
        String[] arr = new String[data.size() - 1];
        for (int i = 0; i < data.size() - 1; i++) {
            arr[i] = data.get(i).getName();
        }
        new AlertDialog.Builder(_mActivity)
                .setItems(arr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        _mActivity.start(CameraSettingFragment.newInstance(data.get(which)));
                    }
                }).show();
    }
}
