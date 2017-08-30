package com.aglhz.s1.more.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.entity.bean.AuthorizationBean;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.more.contract.AuthorizationContract;
import com.aglhz.s1.more.presenter.AuthorizationPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.adialog.ADialogListener;
import cn.itsite.adialog.BaseViewHolder;
import cn.itsite.adialog.dialog.BaseDialog;

/**
 * Author: LiuJia on 2017/8/30 0030 10:22.
 * Email: liujia95me@126.com
 */

public class AuthorizationFragment extends BaseFragment<AuthorizationContract.Presenter> implements AuthorizationContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;

    private Unbinder unbinder;
    private AuthorizationRVAdapter adapter;
    private Params params = Params.getInstance();
    private GatewaysBean.DataBean hostBean;

    public static AuthorizationFragment newInstance(GatewaysBean.DataBean hostBean) {
        AuthorizationFragment fragment = new AuthorizationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", hostBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected AuthorizationContract.Presenter createPresenter() {
        return new AuthorizationPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        hostBean = getArguments().getParcelable("bean");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        if (hostBean == null) {
            DialogHelper.warningSnackbar(getView(), "主机为空");
            return;
        }
        toolbarTitle.setText(hostBean.getName());
        toolbarMenu.setText("授权");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        adapter = new AuthorizationRVAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(adapter);

        mPresenter.requestgatewayAuthList(params);
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = Constants.PAGE_SIZE;
        mPresenter.requestgatewayAuthList(params);
    }

    private void initListener() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_unbound:
                        params.fid = adapter.getItem(position).getFid();
                        mPresenter.requestGatewayUnAuth(params);
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void responsegatewayAuthList(List<AuthorizationBean.DataBean> data) {
        adapter.setNewData(data);
    }

    @Override
    public void responseGatewayAuthSuccesst(BaseBean bean) {
        DialogHelper.successSnackbar(getView(),bean.getOther().getMessage());
        onRefresh();
    }

    @Override
    public void responseGatewayUnAuthSuccesst(BaseBean bean) {
        DialogHelper.successSnackbar(getView(),bean.getOther().getMessage());
        onRefresh();
    }

    @OnClick(R.id.toolbar_menu)
    public void onViewClicked() {
        new BaseDialog(_mActivity)
                .setLayoutId(R.layout.dialog_add_authorization)//传入你的xml布局。
                .setConvertListener(new ADialogListener.OnDialogConvertListener() {
                    @Override
                    public void convert(BaseViewHolder holder, final Dialog dialog) {
                        //通过ViewHolder对View进行一些定制化。
                        EditText etInputPhone = holder.getView(R.id.et_input_phone);
                        holder.setOnClickListener(R.id.tv_comfirm, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(TextUtils.isEmpty(etInputPhone.getText().toString())){
                                    DialogHelper.warningSnackbar(getView(),"请输入电话号码");
                                    return;
                                }
                                params.mobile = etInputPhone.getText().toString();
                                mPresenter.requestGatewayAuth(params);
                                //确定
                                dialog.dismiss();
                            }
                        }).setOnClickListener(R.id.tv_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .show();//显示。
    }
}
