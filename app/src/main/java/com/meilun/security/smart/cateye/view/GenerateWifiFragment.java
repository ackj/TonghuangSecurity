package com.meilun.security.smart.cateye.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.meilun.security.smart.R;
import com.meilun.security.smart.widget.QRimage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ToastUtils;

/**
 * Author： Administrator on 2017/7/20 0020.
 * Email： liujia95me@126.com
 */
public class GenerateWifiFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_wifi_name)
    EditText etWifiName;
    @BindView(R.id.et_wifi_pwd)
    EditText etWifiPwd;
    @BindView(R.id.iv_scan)
    ImageView ivScan;

    Unbinder unbinder;

    public static GenerateWifiFragment newInstance() {
        return new GenerateWifiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cateye_generate_wifi, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("生成WiFi");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
    }


    private void shareWifiInfo() {
        String ssid = etWifiName.getText().toString().trim();
        String passWord = etWifiPwd.getText().toString().trim();
        if (TextUtils.isEmpty(ssid)) {
            ToastUtils.showToast(_mActivity, "请输入WiFi名称");
            return;
        }
//        if (TextUtils.isEmpty(passWord) || passWord.length() < 8) {
//            ToastUtils.showToast(_mActivity, "请输入合法的WiFi密码");
//            return;
//        }
        Bitmap bitmap = QRimage.createQRImage(ssid + "&&" + passWord);
        ivScan.setImageBitmap(bitmap);
    }

    @OnClick({R.id.btn_generate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_generate:
                shareWifiInfo();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
