package com.aglhz.s1.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.appupdate.UpdateAppHttpUtils;
import com.aglhz.s1.entity.bean.AppUpdateBean;
import com.google.gson.Gson;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.AppUtils;

/**
 * Author: LiuJia on 2017/8/11 0011 09:29.
 * Email: liujia95me@126.com
 */

public class AboutUsFragment extends BaseFragment {
    public static final String TAG = AboutUsFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        initStateBar(toolbar);
        toolbarTitle.setText("关于我们");
        tvVersionName.setText("版本：" + AppUtils.getVersionName(App.mContext));
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @OnClick({R.id.tv_check_update,
            R.id.tv_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_check_update:
                updateApp();
                break;
            case R.id.tv_feedback:
                start(FeedbackFragment.newInstance());
                break;
        }
    }

    /**
     * 检测是否有新版本需要下载更新。
     */
    private void updateApp() {
        Map<String, String> params = new HashMap<>();
        params.put("appType", "1");
        new UpdateAppManager
                .Builder()
                .setActivity(_mActivity)
                .setHttpManager(new UpdateAppHttpUtils())
                .setUpdateUrl(ApiService.requestAppUpdatae)
                .setPost(true)
                .setParams(params)
                .dismissNotificationProgress()
                .hideDialogOnDownloading(false)
                .build()
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        ALog.e("json-->" + json);
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
                                .setApkFileUrl("http://aglhzysq.file.alimmdn.com/app/app-release.apk")
//                                .setApkFileUrl(mAppUpdateBean.getData().getUrl())
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

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
                        showLoading();
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
                        dismissLoading();
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp() {
                        DialogHelper.successSnackbar(getView(), "当前版本已是最新版本");
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
