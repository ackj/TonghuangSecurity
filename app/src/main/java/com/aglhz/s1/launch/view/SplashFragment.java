package com.aglhz.s1.launch.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.main.MainActivity;
import com.aglhz.s1.R;

import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class SplashFragment extends BaseFragment {

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private void initData() {
        startActivity(new Intent(_mActivity, MainActivity.class));
    }

    private void initListener() {
    }
}
