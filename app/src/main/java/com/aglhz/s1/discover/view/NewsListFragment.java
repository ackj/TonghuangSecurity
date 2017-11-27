package com.aglhz.s1.discover.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.common.Constants;
import com.aglhz.s1.common.Params;
import com.aglhz.s1.discover.contract.NewsContract;
import com.aglhz.s1.discover.presenter.NewsPresenter;
import com.aglhz.s1.entity.bean.NewsBean;
import com.aglhz.s1.web.WebActivity;
import com.aglhz.s1.widget.PtrHTFrameLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.statemanager.StateManager;

/**
 * Author: LiuJia on 2017/11/23 0023 09:49.
 * Email: liujia95me@126.com
 */

public class NewsListFragment extends BaseFragment<NewsContract.Presenter> implements NewsContract.View {

    public static final String TAG = NewsListFragment.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrHTFrameLayout ptrFrameLayout;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private Params params = Params.getInstance();
    private StateManager mStateManager;
    private NewsListRVAdapter adapter;

    Unbinder unbinder;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @NonNull
    @Override
    protected NewsContract.Presenter createPresenter() {
        return new NewsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initStateManager();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("新闻动态");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new NewsListRVAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                NewsBean.DataBean bean = adapter.getItem(position);
                gotoWeb(bean.getTitle(), bean.getContent());
            }
        });
    }

    @Override
    public void onRefresh() {
        params.pageSize = 10;
        params.page = 1;
        mPresenter.requestNewsList(params);
    }

    private void gotoWeb(String title, String link) {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_LINK, link);
        _mActivity.startActivity(intent);//点击一个商品跳WEB
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyText("暂无内容！")
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setConvertListener((holder, stateLayout) ->
                        holder.setOnClickListener(R.id.bt_empty_state,
                                v -> ptrFrameLayout.autoRefresh())
                                .setText(R.id.bt_empty_state, "点击刷新"))
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);
        if (params.page == 1) {
            //为后面的pageState做准备
            mStateManager.showError();
        } else if (params.page > 1) {
//            adapter.loadMoreFail();
            params.page--;
        }
    }

    @Override
    public void responseNewsList(List<NewsBean.DataBean> datas) {
        ptrFrameLayout.refreshComplete();
        adapter.setNewData(datas);
    }
}
