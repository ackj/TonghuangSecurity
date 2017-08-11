package com.aglhz.s1.security.view;

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
import android.widget.Toast;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.DevicesBean;
import com.aglhz.s1.common.DefenseLineLevel;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.security.contract.AddDetectorContract;
import com.aglhz.s1.security.presenter.AddDetectorPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author： Administrator on 2017/5/2 0002.
 * Email： liujia95me@126.com
 */
public class AddDetectorFragment extends BaseFragment<AddDetectorContract.Presenter> implements AddDetectorContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    Unbinder unbinder;
    private SecurityRVAdapter adapter;
    private Params params = Params.getInstance();

    public static AddDetectorFragment newInstance() {
        return new AddDetectorFragment();
    }

    @NonNull
    @Override
    protected AddDetectorContract.Presenter createPresenter() {
        return new AddDetectorPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_detector, container, false);
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
        toolbarTitle.setText("选择添加探测器");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        adapter = new SecurityRVAdapter();
        recyclerview.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        recyclerview.setAdapter(adapter);
        params.pageSize = 12;
        params.page = 1;
        mPresenter.requestDetectorList(params);
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Toast.makeText(_mActivity, position + "", Toast.LENGTH_SHORT).show();
//            SelectorDialogFragment editNameDialog = new SelectorDialogFragment();
//            editNameDialog.show(getFragmentManager(), "EditNameDialog");
            DevicesBean.DataBean.DeviceTypeListBean bean = adapter.getData().get(position);
            params.roomFid = "117ba3e3-88d6-45f5-bd38-cc952a16daa8";//添加传感器不需要房间号，等基哥删除掉。
            params.sensorType = bean.getCode();
            params.name = bean.getName();
            params.defenseLevel = DefenseLineLevel.DLL_FIRST;
            showLoading();
            mPresenter.requestAddDetector(params);
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
    }

    @Override
    public void responseDetectorList(List<DevicesBean.DataBean.DeviceTypeListBean> data) {
        adapter.setNewData(data);
    }

    @Override
    public void responseAddDetector(BaseBean bean) {
        dismissLoading();
        DialogHelper.successSnackbar(getView(), "添加成功");
    }
}
