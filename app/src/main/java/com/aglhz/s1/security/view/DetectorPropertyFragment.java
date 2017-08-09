package com.aglhz.s1.security.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.BaseBean;
import cn.itsite.abase.common.DialogHelper;
import com.aglhz.s1.security.contract.DetectorPropertyContract;
import com.aglhz.s1.security.presenter.DetectorPropertyPresenter;
import com.dd.CircularProgressButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: 2017/5/2 0002.
 * Email:liujia95me@126.com
 */
public class DetectorPropertyFragment extends BaseFragment<DetectorPropertyContract.Presenter> implements DetectorPropertyContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cpb_delete_fragment_detector_property)
    CircularProgressButton cpbDelete;
    Unbinder unbinder;

    public static DetectorPropertyFragment newInstance() {
        return new DetectorPropertyFragment();
    }

    @NonNull
    @Override
    protected DetectorPropertyContract.Presenter createPresenter() {
        return new DetectorPropertyPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detector_property, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("探测器属性");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.cpb_delete_fragment_detector_property)
    public void onViewClicked() {
        cpbDelete.setIndeterminateProgressMode(true);

        cpbDelete.setProgress(50);
        cpbDelete.postDelayed(() -> {
            cpbDelete.setProgress(100);
            cpbDelete.setProgress(0);
        }, 1000);


    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseDetectorProperty(BaseBean baseBean) {

    }
}
