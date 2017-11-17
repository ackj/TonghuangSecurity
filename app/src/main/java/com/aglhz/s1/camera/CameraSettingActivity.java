package com.aglhz.s1.camera;

import android.os.Bundle;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.DeviceListBean;

import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * Author: LiuJia on 2017/11/16 0016 09:00.
 * Email: liujia95me@126.com
 */

public class CameraSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            DeviceListBean.DataBean.SubDevicesBean bean = (DeviceListBean.DataBean.SubDevicesBean) getIntent().getSerializableExtra("bean");
            loadRootFragment(R.id.fl_main_activity, CameraSettingFragment.newInstance(bean));
        }
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }
}
