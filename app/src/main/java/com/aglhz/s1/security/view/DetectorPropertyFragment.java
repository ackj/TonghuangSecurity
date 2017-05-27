package com.aglhz.s1.security.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.dd.CircularProgressButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: 2017/5/2 0002.
 * Email:liujia95me@126.com
 */
public class DetectorPropertyFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cpb_delete_fragment_detector_property)
    CircularProgressButton cpbDelete;
    Unbinder unbinder;

    public static DetectorPropertyFragment newInstance() {
        return new DetectorPropertyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detector_property, container, false);
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
        toolbarTitle.setText("探测器属性");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    Handler handler = new Handler(new Handler.Callback() {
//        int i = 0;
//
//        @Override
//        public boolean handleMessage(Message msg) {
//            if (i <= 100) {
//                cpbDeleteDetector.setProgress(i);
//                handler.sendEmptyMessageDelayed(100, 30);
//                i++;
//            }
//            return false;
//        }
//    });

    @OnClick(R.id.cpb_delete_fragment_detector_property)
    public void onViewClicked() {
//        handler.sendEmptyMessageDelayed(100, 30);

        cpbDelete.setIndeterminateProgressMode(true);

        cpbDelete.setProgress(50);
        cpbDelete.postDelayed(new Runnable() {
            @Override
            public void run() {
                cpbDelete.setProgress(100);
                cpbDelete.setProgress(0);
            }
        }, 1000);


    }
}
