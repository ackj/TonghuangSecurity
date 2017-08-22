package com.aglhz.s1.room.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.s1.R;
import com.aglhz.s1.entity.bean.DeviceButtonBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ToastUtils;

/**
 * Author: LiuJia on 2017/5/17 0017 09:41.
 * Email: liujia95me@126.com
 */

public class RoomFragment extends BaseFragment {
    private static final String TAG = RoomFragment.class.getSimpleName();
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
    List<TextView> textViews;
    Unbinder unbinder;
    private int selectPostion;
    private List<List<DeviceButtonBean>> coordinateLists = new ArrayList<>();

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
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
        list1.add(new DeviceButtonBean(0.493f, 0.162f));
        list1.add(new DeviceButtonBean(0.32f, 0.540f));
        list1.add(new DeviceButtonBean(0.64f, 0.540f));
        list1.add(new DeviceButtonBean(0.0987f, 0.129f));
        List<DeviceButtonBean> list2 = new ArrayList<>();
        list2.add(new DeviceButtonBean(0.493f, 0.162f));
        list2.add(new DeviceButtonBean(0.112f, 0.534f));
        list2.add(new DeviceButtonBean(0.693f, 0.703f));
        list2.add(new DeviceButtonBean(-0.3f, 0.129f));
        List<DeviceButtonBean> list3 = new ArrayList<>();
        list3.add(new DeviceButtonBean(0.267f, 0.146f));
        list3.add(new DeviceButtonBean(0.08f, 0.518f));
        list3.add(new DeviceButtonBean(0.68f, 0.761f));
        list3.add(new DeviceButtonBean(-0.3f, 0.129f));
        List<DeviceButtonBean> list4 = new ArrayList<>();
        list4.add(new DeviceButtonBean(0.08f, 0.092f));
        list4.add(new DeviceButtonBean(0.52f, 0.461f));
        list4.add(new DeviceButtonBean(0.053f, 0.738f));
        list4.add(new DeviceButtonBean(-0.3f, 0.129f));
        coordinateLists.add(list1);
        coordinateLists.add(list2);
        coordinateLists.add(list3);
        coordinateLists.add(list4);
        textViews = new ArrayList<>();
        textViews.add(tvRoomdevicebutton1);
        textViews.add(tvRoomdevicebutton2);
        textViews.add(tvRoomdevicebutton3);
        textViews.add(tvRoomdevicebutton4);
        for (int i = 0; i < list1.size(); i++) {
            textViews.get(i).setX(list1.get(i).x);
            textViews.get(i).setY(list1.get(i).y);
        }
    }

    private void initListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ALog.e(TAG, "onPageScrolled --> position:" + position + " --positionOffset:" + positionOffset + " --positionOffsetPixels:" + positionOffsetPixels);
                setButtonXY(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                selectPostion = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //动态设置按钮的XY坐标
    public void setButtonXY(int position, float positionOffset) {
        if (position == coordinateLists.size() - 1) {
            return;
        }
        List<DeviceButtonBean> buttonBeanList1 = coordinateLists.get(position);
        List<DeviceButtonBean> buttonBeanList2 = coordinateLists.get(position + 1);
        for (int i = 0; i < textViews.size(); i++) {
            //拿到此时坐标
            int buttonBean1_0X = buttonBeanList1.get(i).x;
            int buttonBean1_0Y = buttonBeanList1.get(i).y;
            //拿到未来坐标
            int buttonBean2_0X = buttonBeanList2.get(i).x;
            int buttonBean2_0Y = buttonBeanList2.get(i).y;
            //拿到两者坐标差值
            int dx_value_0 = buttonBean2_0X - buttonBean1_0X;
            int dy_value_0 = buttonBean2_0Y - buttonBean1_0Y;
            float pivotX = dx_value_0 * positionOffset;
            float pivotY = dy_value_0 * positionOffset;
            int x = (int) (buttonBean1_0X + pivotX);
            int y = (int) (buttonBean1_0Y + pivotY);
            textViews.get(i).setX(x);
            textViews.get(i).setY(y);
        }
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
                clickButtonFromPos("灯光");
                break;
            case R.id.tv_roomdevicebutton_2:
                clickButtonFromPos("插座");
                break;
            case R.id.tv_roomdevicebutton_3:
                clickButtonFromPos("警号");
                break;
            case R.id.tv_roomdevicebutton_4:
                clickButtonFromPos("监控");
                break;
        }
    }

    private void clickButtonFromPos(String name){
        ToastUtils.showToast(_mActivity,name+selectPostion);
    }

}
