package com.aglhz.s1.dialog;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.s1.App;
import com.aglhz.s1.R;
import com.aglhz.s1.database.DBManager;
import com.aglhz.s1.database.ItemBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: LiuJia on 2017/5/6 0006 15:45.
 * Email: liujia95me@126.com
 */

public class SelectorVPAdapter extends PagerAdapter {

    List<String> mTitles = new ArrayList<>();
    private HashMap<Integer, List<ItemBean>> mHmDatas = new HashMap<>();
    private DBManager dbManager;

    public SelectorVPAdapter() {
        //数据初始化
        mTitles.add("请选择");
        dbManager = new DBManager(App.mContext);
        List<ItemBean> province = dbManager.query(null, "Province");
        mHmDatas.put(0, province);
    }

    private int mChildCount = 0;

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        List<ItemBean> lists = mHmDatas.get(position);

        final RecyclerView recyclerView = new RecyclerView(container.getContext());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).isSelect) {
                recyclerView.scrollToPosition(i);
                break;
            }
        }

        final SelectorItemRVAdapter rvAdapter = new SelectorItemRVAdapter(lists);
        recyclerView.setAdapter(rvAdapter);
        container.addView(recyclerView);

        final int vpPosition = position;
        rvAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.text1:
                        //获取点击该item的信息
                        List<ItemBean> itemBeans = mHmDatas.get(vpPosition);
                        ItemBean bean = itemBeans.get(position);
                        for (int i = 0; i < itemBeans.size(); i++) {
                            itemBeans.get(i).isSelect = false;
                        }
                        bean.isSelect = true;
                        bean.selectPosition = position;

                        //重设Titls
                        resetTitls(vpPosition, bean.name);

                        List<ItemBean> query = dbManager.query(bean, null);
                        if (query != null && query.size() != 0) {
                            mTitles.add("请选择");
                            mHmDatas.put(vpPosition + 1, query);
                        }
                        //刷新viewpager
                        notifyDataSetChanged();

                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick();
                        }
                        break;
                }
            }
        });
        return recyclerView;
    }


    private void resetTitls(int clickVpIndex, String title) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < clickVpIndex; i++) {
            data.add(mTitles.get(i));
        }
        mTitles.clear();
        mTitles.addAll(data);
        mTitles.add(title);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick();
    }
}
