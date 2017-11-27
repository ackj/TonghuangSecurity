package com.meilun.security.smart.launch.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.main.MainActivity;
import com.meilun.security.smart.R;

import cn.itsite.abase.cache.SPCache;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
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
