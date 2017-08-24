//package com.aglhz.s1.room.view;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.aglhz.s1.R;
//import com.aglhz.s1.entity.bean.SecurityBean1;
//import com.aglhz.s1.security.view.AddDetectorRVAdapter;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import me.yokeyword.fragmentation.SupportFragment;
//
///**
// * Author: LiuJia on 2017/4/27 0027 10:21.
// * Email: liujia95me@126.com
// */
//
//public class RoomDeviceFragment extends SupportFragment {
//
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    Unbinder unbinder;
//    private AddDetectorRVAdapter adapter;
//
//    public static RoomDeviceFragment newInstance() {
//        return new RoomDeviceFragment();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_room_device, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initData();
//        initListener();
//    }
//
//    private void initData() {
//        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
//        List<SecurityBean1> data = new ArrayList<>();
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "门槛"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "红外"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "红外对射"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "玻璃破碎"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "漏水"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "紧急按钮"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "气体"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "添加探测器"));
//        data.add(new SecurityBean1(R.mipmap.ic_logo, "添加"));
////        adapter = new AddDetectorRVAdapter(data);
//        View headerView = LayoutInflater.from(_mActivity).inflate(R.layout.item_security_header, null);
//        adapter.setHeaderView(headerView);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void initListener() {
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (position == adapter.getData().size() - 1) {
//                    _mActivity.start(AddDeviceFragment.newInstance());
//                } else {
//                    _mActivity.start(DevicePropertyFragment.newInstance());
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//}
