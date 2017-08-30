package com.aglhz.s1.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.appupdate.UpdateAppHttpUtils;
import com.aglhz.s1.entity.bean.AppUpdateBean;
import com.aglhz.s1.history.view.DeviceLogsFragment;
import com.aglhz.s1.more.view.MoreFragment;
import com.aglhz.s1.room.view.RoomDeviceListFragment;
import com.aglhz.s1.scene.view.SceneFragment;
import com.aglhz.s1.security.view.SecurityFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.gson.Gson;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.AppUtils;
import cn.itsite.abase.utils.ToastUtils;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/4/27 0027 11:12.
 * Email: liujia95me@126.com
 */

public class MainFragment extends BaseFragment {
    public static final String TAG = MainFragment.class.getSimpleName();
    private static final long WAIT_TIME = 2000L;// 再点一次退出程序时间设置
    private long TOUCH_TIME = 0;
    private AHBottomNavigation ahbn;
    private SupportFragment[] fragments = new SupportFragment[5];
    private int bottomNavigationPreposition;
    private static final String KEY_CURR_POSITION = "bottomNavigationPreposition";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ahbn = (AHBottomNavigation) view.findViewById(R.id.ahbn_main_fragment);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            fragments[0] = SecurityFragment.newInstance();
            fragments[1] = RoomDeviceListFragment.newInstance();
            fragments[2] = SceneFragment.newInstance();
            fragments[3] = DeviceLogsFragment.newInstance();
            fragments[4] = MoreFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container_main_fragment, 0, fragments[0], fragments[1], fragments[2], fragments[3], fragments[4]);
        } else {
            fragments[0] = findChildFragment(SecurityFragment.class);
            fragments[1] = findChildFragment(RoomDeviceListFragment.class);
            fragments[2] = findChildFragment(SceneFragment.class);
            fragments[3] = findChildFragment(DeviceLogsFragment.class);
            fragments[4] = findChildFragment(MoreFragment.class);
            bottomNavigationPreposition = savedInstanceState.getInt(KEY_CURR_POSITION);
        }
        initData();
    }


    private void initData() {
        updateApp();//检测App的更新。

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.security, R.drawable.ic_navigationsecurity_black_78px, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.room, R.drawable.ic_navigationroom_black_78px, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.scene, R.drawable.ic_navigationscenes_black_78px, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.hisotry, R.drawable.ic_navigationhistory_black_78px, R.color.white);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.more, R.drawable.ic_navigationmore_black_78px, R.color.white);
        List<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);
        ahbn.addItems(bottomNavigationItems);
        ahbn.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        ahbn.setBehaviorTranslationEnabled(false);
        ahbn.setColored(true);
        ahbn.setForceTint(false);
        ahbn.setAccentColor(getResources().getColor(R.color.base_color));
        ahbn.setInactiveColor(getResources().getColor(R.color.black));
        ahbn.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        ahbn.setOnTabSelectedListener((position, wasSelected) -> {
            showHideFragment(fragments[position], fragments[bottomNavigationPreposition]);
            bottomNavigationPreposition = position;
            return true;
        });
        ahbn.setCurrentItem(bottomNavigationPreposition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURR_POSITION, bottomNavigationPreposition);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            //退到桌面，而不是退出应用，让用户以为退出应用，尽量保活。
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtils.showToast(App.mContext, Constants.PRESS_AGAIN);
        }
        return true;
    }

    /**
     * 检测是否有新版本需要下载更新。
     */
    private void updateApp() {
        ALog.e("requestAppUpdatae-->" + ApiService.requestAppUpdatae);
        Map<String, String> params = new HashMap<String, String>();
        params.put("appType", "1");

        new UpdateAppManager
                .Builder()
                .setActivity(_mActivity)
                .setHttpManager(new UpdateAppHttpUtils())
                .setPost(true)
                .setParams(params)
                .setUpdateUrl(ApiService.requestAppUpdatae)
                .hideDialogOnDownloading(false)
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        ALog.e("requestAppUpdatae-->" + json);
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        AppUpdateBean mAppUpdateBean = new Gson().fromJson(json, AppUpdateBean.class);

                        if (AppUtils.getVersionCode(_mActivity) < mAppUpdateBean.getData().getVersionCode()) {
                            updateAppBean.setUpdate("Yes");
                        } else {
                            updateAppBean.setUpdate("No");
                        }

                        updateAppBean
                                //（必须）是否更新Yes,No
                                .setUpdate(AppUtils.getVersionCode(_mActivity) < mAppUpdateBean.getData().getVersionCode() ? "Yes" : "No")
                                //（必须）新版本号，
                                .setNewVersion(mAppUpdateBean.getData().getVersionName())
                                //（必须）下载地址
                                .setApkFileUrl(mAppUpdateBean.getData().getUrl())
                                //（必须）更新内容
                                .setUpdateLog(mAppUpdateBean.getData().getDescription())
                                //大小，不设置不显示大小，可以不设置
                                .setTargetSize(mAppUpdateBean.getData().getSize())
                                //是否强制更新，可以不设置
                                .setConstraint(false);

                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }
                });
    }
}
