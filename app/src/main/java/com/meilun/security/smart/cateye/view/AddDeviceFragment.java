//package com.meilun.security.smart.cateye.view;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.Toolbar;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.Player.Core.PlayerClient;
//import com.Player.web.response.ResponseCommon;
//import com.Player.web.websocket.ClientCore;
//import com.meilun.security.smart.R;
//import com.meilun.security.smart.cateye.contract.AddDeviceContract;
//import com.meilun.security.smart.common.Params;
//import com.meilun.security.smart.common.UserHelper;
//import com.meilun.security.smart.entity.bean.BaseBean;
//
//import org.greenrobot.eventbus.EventBus;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import cn.itsite.abase.common.DialogHelper;
//import cn.itsite.abase.log.ALog;
//import cn.itsite.abase.mvp.view.base.BaseFragment;
//import cn.itsite.abase.utils.KeyBoardUtils;
//import me.yokeyword.fragmentation.SupportFragment;
//
///**
// * Author: LiuJia on 2017/5/4 0004 19:11.
// * Email: liujia95me@126.com
// */
//
//public class AddDeviceFragment extends BaseFragment<AddDeviceContract.Presenter> implements TextWatcher, AddDeviceContract.View {
//    public static final String TAG = AddDeviceFragment.class.getSimpleName();
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    Unbinder unbinder;
//    @BindView(R.id.et_device_name_add_fragment)
//    EditText etDeviceName;
//    @BindView(R.id.et_serial_number_add_fragment)
//    EditText etSerialNumber;
//    @BindView(R.id.et_password_add_fragment)
//    EditText etPassword;
//    @BindView(R.id.bt_add_add_fragment)
//    Button btAdd;
//    @BindView(R.id.iv_qrcode)
//    ImageView ivQrcode;
//    private Params params = Params.getInstance();
//
//    public static AddDeviceFragment newInstance() {
//        return new AddDeviceFragment();
//    }
//
//    @NonNull
//    @Override
//    protected AddDeviceContract.Presenter createPresenter() {
//        return new AddDevicePresenter(this);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_add_device, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        return attachToSwipeBack(view);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initToolbar();
//        initData();
//    }
//
//    private void initToolbar() {
//        initStateBar(toolbar);
//        toolbarTitle.setText("添加设备");
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
//        toolbar.setNavigationOnClickListener(v -> {
//            pop();
//        });
//    }
//
//    private void initData() {
//        etDeviceName.addTextChangedListener(this);
//        etSerialNumber.addTextChangedListener(this);
//        params.account = UserHelper.account;
//        etPassword.addTextChangedListener(this);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        KeyBoardUtils.hideKeybord(etDeviceName, _mActivity);
//        unbinder.unbind();
//    }
//
//    @OnClick({R.id.iv_qrcode, R.id.bt_add_add_fragment})
//    public void onViewClicked(View view) {
//        KeyBoardUtils.hideKeybord(view, _mActivity);
//        switch (view.getId()) {
//            case R.id.iv_qrcode:
//                startForResult(QRCodeFragment.newInstance(), SupportFragment.RESULT_OK);
//                break;
//            case R.id.bt_add_add_fragment:
//                params.deviceName = etDeviceName.getText().toString();
//                params.serialNumber = etSerialNumber.getText().toString();
//                params.password = etPassword.getText().toString();
//                showLoading();
//                mPresenter.requestAddDevice(params);
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        boolean enabled = TextUtils.isEmpty(etDeviceName.getText().toString())
//                || TextUtils.isEmpty(etSerialNumber.getText().toString())
//                || TextUtils.isEmpty(etPassword.getText().toString());
//        btAdd.setEnabled(!enabled);
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//
//    }
//
//    int vendorID(String umid) {
//        if (umid.length() == 16) {
//            return PlayerClient.NPC_D_MON_VENDOR_ID_HZXM;
//        }
//        if ((umid.substring(0, 2).contains("xm")) || (umid.substring(0, 2).contains("Xm")) || (umid.substring(0, 2).contains("xM"))) {
//            return PlayerClient.NPC_D_MON_VENDOR_ID_HZXM;
//        }
//        return PlayerClient.NPC_D_MON_VENDOR_ID_UMSP;
//    }
//
//    @Override
//    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
//        super.onFragmentResult(requestCode, resultCode, data);
//        if (data != null) {
//            etSerialNumber.setText(data.getString(Constants.SERIAL_NUMBER));
//        }
//    }
//
//    @Override
//    public void responseAddSuccess(BaseBean baseBean) {
//        ALog.e(TAG, "保存成功");
//        ClientCore.getInstance().addNodeInfo(params.deviceName, 0, 2, 2,
//                vendorID(params.serialNumber), params.serialNumber, "", 0,
//                "admin", params.password, 0, 0, 0, new Handler() {
//
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        dismissLoading();
//                        ResponseCommon response = (ResponseCommon) msg.obj;
//                        if ((response != null) && (response.h != null) && (response.h.e == 200)) {
//                            ALog.e(response.h.e);
//                            DialogHelper.successSnackbar(getView(), "恭喜，添加成功!");
//                            EventBus.getDefault().post(new EventRefreshDeviceList());
//                            //添加到我们的服务器
//                            pop();
//                        } else {
//                            DialogHelper.errorSnackbar(getView(), "抱歉，添加失败!");
//                        }
//                    }
//                });
//    }
//}
