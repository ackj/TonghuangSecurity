package com.aglhz.s1.web;

import android.os.Bundle;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 *
 * 负责项目中的web部分。
 */

public class WebActivity extends BaseActivity {
    private static final String TAG = WebActivity.class.getSimpleName();
    private String title;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initData();

        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_web_activity, WebFragment.newInstance(title, link));
        }
    }

    private void initData() {
        title = getIntent().getStringExtra(Constants.KEY_TITLE);
        link = getIntent().getStringExtra(Constants.KEY_LINK);
        ALog.e(link);
    }
}
