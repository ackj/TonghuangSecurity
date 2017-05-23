package com.aglhz.s1.room.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.DeviceButtonBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author: LiuJia on 2017/5/17 0017 09:41.
 * Email: liujia95me@126.com
 */

public class RoomFragment2 extends BaseFragment {

    private static final String TAG = RoomFragment2.class.getSimpleName();

    @BindView(R.id.viewpager_room_fragment)
    ViewPager viewpager;
    @BindView(R.id.tv_roomdevicebutton_1)
    TextView tvRoomdevicebutton1;
    @BindView(R.id.tv_roomdevicebutton_2)
    TextView tvRoomdevicebutton2;
    @BindView(R.id.tv_roomdevicebutton_3)
    TextView tvRoomdevicebutton3;
    @BindView(R.id.tv_roomdevicebutton_4)
    TextView tvRoomdevicebutton4;

    Unbinder unbinder;

    private List<List<DeviceButtonBean>> coordinateLists = new ArrayList<>();

    public static RoomFragment2 newInstance() {
        return new RoomFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private void initData() {
        RoomVPAdapter adapter = new RoomVPAdapter();
        viewpager.setAdapter(adapter);

        List<DeviceButtonBean> list1 = new ArrayList<>();
        list1.add(new DeviceButtonBean(0.65f, 0.2f));
        list1.add(new DeviceButtonBean(0.4f, 0.65f));
        list1.add(new DeviceButtonBean(0.8f, 0.65f));
        list1.add(new DeviceButtonBean(0.15f, 0.2f));

        List<DeviceButtonBean> list2 = new ArrayList<>();
        list2.add(new DeviceButtonBean(0.65f, 0.2f));
        list2.add(new DeviceButtonBean(0.2f, 0.65f));
        list2.add(new DeviceButtonBean(0.85f, 0.8f));
        list2.add(new DeviceButtonBean(-0.1f, -0.1f));

        List<DeviceButtonBean> list3 = new ArrayList<>();
        list3.add(new DeviceButtonBean(0.3f, 0.2f));
        list3.add(new DeviceButtonBean(0.2f, 0.65f));
        list3.add(new DeviceButtonBean(0.85f, 0.8f));
        list3.add(new DeviceButtonBean(-0.1f, -0.1f));

        List<DeviceButtonBean> list4 = new ArrayList<>();
        list4.add(new DeviceButtonBean(0.1f, 0.2f));
        list4.add(new DeviceButtonBean(0.6f, 0.55f));
        list4.add(new DeviceButtonBean(0.1f, 0.8f));
        list4.add(new DeviceButtonBean(-0.1f, -0.1f));

        coordinateLists.add(list1);
        coordinateLists.add(list2);
        coordinateLists.add(list3);
        coordinateLists.add(list4);
    }

    private void initListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ALog.e(TAG, "onPageScrolled --> position:" + position + " --positionOffset:" + positionOffset + " --positionOffsetPixels:" + positionOffsetPixels);
                setButtonXY2(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                setButtonXY(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setButtonXY2(int position,float positionOffset) {
        List<DeviceButtonBean> buttonBeanList1 = coordinateLists.get(position);
        int buttonBean1_0X = buttonBeanList1.get(0).x;
        int buttonBean1_0Y = buttonBeanList1.get(0).y;

        List<DeviceButtonBean> buttonBeanList2 = coordinateLists.get(position + 1);
        int buttonBean2_0X = buttonBeanList2.get(0).x;
        int buttonBean2_0Y = buttonBeanList2.get(0).y;

        int dx_value_0 =  buttonBean2_0X - buttonBean1_0X;
        int dy_value_0 =  buttonBean2_0Y - buttonBean1_0Y;

        float pivotX = dx_value_0 * positionOffset;
        float pivotY = dy_value_0 * positionOffset;


        ALog.d(TAG,"setButtonXY2 pivotX:"+pivotX);
        ALog.d(TAG,"setButtonXY2 pivotY:"+pivotY);

        tvRoomdevicebutton1.setTranslationX(pivotX);
        tvRoomdevicebutton1.setTranslationY(pivotY);

    }

    public void setButtonXY(int position) {
        List<DeviceButtonBean> buttonBeanList = coordinateLists.get(position);
        tvRoomdevicebutton1.setPivotX(buttonBeanList.get(0).x);
        tvRoomdevicebutton1.setPivotY(buttonBeanList.get(0).y);

        tvRoomdevicebutton2.setPivotX(buttonBeanList.get(1).x);
        tvRoomdevicebutton2.setPivotY(buttonBeanList.get(1).y);

        tvRoomdevicebutton3.setPivotX(buttonBeanList.get(2).x);
        tvRoomdevicebutton3.setPivotY(buttonBeanList.get(2).y);

        tvRoomdevicebutton4.setPivotX(buttonBeanList.get(3).x);
        tvRoomdevicebutton4.setPivotY(buttonBeanList.get(3).y);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_roomdevicebutton_1, R.id.tv_roomdevicebutton_2, R.id.tv_roomdevicebutton_3, R.id.tv_roomdevicebutton_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_roomdevicebutton_1:

                break;
            case R.id.tv_roomdevicebutton_2:
                break;
            case R.id.tv_roomdevicebutton_3:
                break;
            case R.id.tv_roomdevicebutton_4:
                break;
        }
    }

}
