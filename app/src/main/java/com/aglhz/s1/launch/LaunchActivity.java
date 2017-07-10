package com.aglhz.s1.launch;

import android.os.Bundle;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.launch.view.GuideFragment;
import com.aglhz.s1.launch.view.SplashFragment;

import cn.itsite.abase.cache.SPCache;
import cn.itsite.abase.mvp.view.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        if(savedInstanceState == null){
            boolean isFirstEnter = (boolean) SPCache.get(App.mContext, Constants.IS_FIRST_ENTER,true);
            //第一次启动应用进入引导页
            if(isFirstEnter){
                loadRootFragment(R.id.fl_launch_activity, GuideFragment.newInstance());
            }else{
                loadRootFragment(R.id.fl_launch_activity, SplashFragment.newInstance());
            }
        }
    }
}
