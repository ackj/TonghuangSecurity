package com.aglhz.s1.launch.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.main.MainActivity;
import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;

import cn.itsite.abase.cache.SPCache;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class GuideFragment extends BaseFragment {

    public static GuideFragment newInstance() {
        return new GuideFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private void initData() {
        SPCache.put(_mActivity, Constants.IS_FIRST_ENTER, true);
        startActivity(new Intent(_mActivity, MainActivity.class));
        _mActivity.finish();
    }

    private void initListener() {
    }
}
