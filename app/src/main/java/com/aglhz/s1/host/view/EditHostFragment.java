package com.aglhz.s1.host.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.ApiService;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.GatewaysBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.common.RxManager;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by leguang on 2017/6/22 0022.
 * Email：langmanleguang@qq.com
 */

public class EditHostFragment extends BaseFragment {
    public static final String TAG = EditHostFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_name_edit_host_fragment)
    EditText etName;
    private Unbinder unbinder;
    private GatewaysBean.DataBean hostBean;
    private RxManager rxManager = new RxManager();
    private Params params = Params.getInstance();

    public static EditHostFragment newInstance(GatewaysBean.DataBean hostBean) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.KEY_HOST, hostBean);
        EditHostFragment fragment = new EditHostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            hostBean = args.getParcelable(Constants.KEY_HOST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_host, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initData() {
        params.gateway = hostBean.getFid();
        etName.setText(hostBean.getName());
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("主机名称");
        toolbarMenu.setText("保存");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        rxManager.clear();
    }

    @OnClick(R.id.toolbar_menu)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            DialogHelper.warningSnackbar(getView(), "主机名称不能为空！");
            return;
        }

        params.name = etName.getText().toString();
        rxManager.add(HttpHelper.getService(ApiService.class)
                .requestUpdateHostName(ApiService.requestUpdateHostName,
                        params.token,
                        params.gateway,
                        params.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_SUCCESS) {
                        DialogHelper.successSnackbar(getView(), baseBean.getOther().getMessage());
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.KEY_HOST_NAME, params.name);
                        setFragmentResult(HostSettingsFragment.UPDATE_HOST_NAME, bundle);
                    } else {
                        error(baseBean.getOther().getMessage());
                    }
                }, this::error, () -> complete(null), disposable -> start("")));
    }
}
