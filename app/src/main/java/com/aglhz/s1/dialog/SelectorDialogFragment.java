package com.aglhz.s1.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.aglhz.s1.BaseApplication;
import com.aglhz.s1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.utils.DensityUtils;

/**
 * Author: LiuJia on 2017/5/6 0006 15:42.
 * Email: liujia95me@126.com
 */

public class SelectorDialogFragment extends DialogFragment {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private Unbinder unbinder;

//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView()
////        View view = inflater.inflate(R.layout.fragment_dialog_selector, container);
////        unbinder = ButterKnife.bind(this, view);
////        return view;
//    }

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongConstant")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.Base_AlertDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.fragment_dialog_selector);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = DensityUtils.dp2px(BaseApplication.mContext, 326);
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        window.setAttributes(lp);

        unbinder = ButterKnife.bind(this, dialog); // Dialog即View
        initData();
        initListener();
        return dialog;
    }

    private void initData() {
        final SelectorVPAdapter adapter = new SelectorVPAdapter();
        tablayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectorVPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
//                viewpager.setOffscreenPageLimit(adapter.getCount());
                viewpager.setCurrentItem(adapter.getCount());
            }
        });
    }

    private void initListener() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
