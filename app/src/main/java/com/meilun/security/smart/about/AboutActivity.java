package com.meilun.security.smart.about;

import android.os.Bundle;

import com.meilun.security.smart.about.view.AboutUsFragment;
import com.meilun.security.smart.R;

import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * [关于我们]的父容器。
 */
public class AboutActivity extends BaseActivity {
    private static final String TAG = AboutActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, AboutUsFragment.newInstance());
        }
    }
}