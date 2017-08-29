package com.aglhz.s1.more.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.location.LoacationFragment;
import com.aglhz.s1.more.contract.AddHostContract;
import com.aglhz.s1.more.presenter.AddHostPresenter;
import com.amap.api.services.core.PoiItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: LiuJia on 2017/5/2 0002 20:46.
 * Email: liujia95me@126.com
 */

public class AddHostFragment extends BaseFragment<AddHostContract.Presenter> implements AddHostContract.View {
    public static final String TAG = AddHostFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_device_code_add_host_fragment)
    EditText etDeviceCode;
    @BindView(R.id.et_name_add_host_fragment)
    EditText etName;
    @BindView(R.id.tv_location_add_host_fragment)
    TextView tvLocation;
    @BindView(R.id.ll_location_add_host_fragment)
    LinearLayout llLocation;
    @BindView(R.id.et_address_add_host_fragment)
    EditText etAddress;
    @BindView(R.id.bt_save_add_host_fragment)
    Button btSave;
    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static AddHostFragment newInstance(String hostNumber) {
        Bundle args = new Bundle();
        args.putString(Constants.KEY_HOST_NUMBER, hostNumber);
        AddHostFragment fragment = new AddHostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            params.no = args.getString(Constants.KEY_HOST_NUMBER);
        }
    }

    @NonNull
    @Override
    protected AddHostContract.Presenter createPresenter() {
        return new AddHostPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_host, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initData() {
        etDeviceCode.setText(params.no);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("添加主机");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void responseAddHost(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), baseBean.getOther().getMessage());
        pop();
    }

    @OnClick({R.id.ll_location_add_host_fragment, R.id.bt_save_add_host_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_location_add_host_fragment:
                startForResult(LoacationFragment.newInstance(), SupportFragment.RESULT_OK);
                break;
            case R.id.bt_save_add_host_fragment:
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    DialogHelper.errorSnackbar(getView(), "主机名称不能为空！");
                    return;
                }
                params.name = etName.getText().toString();
                params.no = etDeviceCode.getText().toString().trim();
                mPresenter.requestAddHost(params);
                break;
        }
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        ALog.e("11111111");
        if (data != null) {
            PoiItem poiItem = data.getParcelable(LoacationFragment.POI);
            if (poiItem != null) {
                tvLocation.setText(poiItem.toString());
            }
        }
    }
}
