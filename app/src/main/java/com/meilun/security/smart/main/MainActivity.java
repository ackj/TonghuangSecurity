package com.meilun.security.smart.main;

import android.os.Bundle;

import com.meilun.security.smart.main.view.MainFragment;
import com.meilun.security.smart.R;

import cn.itsite.abase.mvp.view.base.BaseActivity;
/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, MainFragment.newInstance());
        }
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }
}
