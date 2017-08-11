package com.aglhz.s1.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.scene.SceneListRVAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/8/11 0011 09:29.
 * Email: liujia95me@126.com
 */

public class AboutUsFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Unbinder unbinder;
    private SceneListRVAdapter adapter;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();

    }

    private void initData() {
        initStateBar(toolbar);
        toolbarTitle.setText("关于我们");
    }

    private void initListener() {
    }

    @OnClick({R.id.tv_check_update,
            R.id.tv_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_check_update:

                break;
            case R.id.tv_feedback:
                start(FeedbackFragment.newInstance());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
