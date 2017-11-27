package com.meilun.security.smart.login;

import android.Manifest;
import android.os.Bundle;

import com.meilun.security.smart.login.view.LoginFragment;
import com.meilun.security.smart.App;
import com.meilun.security.smart.R;

import java.util.List;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseActivity;
import cn.itsite.abase.utils.ToastUtils;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class LoginActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int SMS = 122;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, LoginFragment.newInstance());
        }
    }

    @AfterPermissionGranted(SMS)
    private void sms() {
        String[] perms = {Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS};
        if (EasyPermissions.hasPermissions(this, perms)) {
            //有权限就直接进行定位操作
//            ToastUtils.showToast(App.mContext, "正在定位……");
//            initLocate();最好还是不需要定位，只记录手动选择的城市和小区。定位只是辅助筛选。

            ALog.e(TAG, "111111");

        } else {
            ALog.e(TAG, "22222");
            EasyPermissions.requestPermissions(this, "应用需要接收短信验证", SMS, perms);
            ToastUtils.showToast(App.mContext, "申请权限……");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ALog.e(TAG, "333333");

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
        ALog.e(TAG, "44444");

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
        ALog.e(TAG, "55555555");

    }
}
