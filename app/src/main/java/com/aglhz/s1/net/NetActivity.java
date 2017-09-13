package com.aglhz.s1.net;

import android.os.Bundle;

import com.aglhz.s1.R;
import com.aglhz.s1.net.view.SetNetFragment;

import cn.itsite.abase.mvp.view.base.BaseActivity;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 配网模块。
 */

public class NetActivity extends BaseActivity {
    private static final String TAG = NetActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, SetNetFragment.newInstance());
        }
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }
}
