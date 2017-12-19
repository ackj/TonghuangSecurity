//package com.meilun.security.smart.cateye.view;
//
//import com.Player.web.response.DevItemInfo;
//import com.Player.web.response.DevState;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.meilun.security.smart.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.itsite.abase.log.ALog;
//import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
//
///**
// * Author: LiuJia on 2017/5/4 0004 14:10.
// * Email: liujia95me@126.com
// */
//
//public class DeviceRVAdapter extends BaseRecyclerViewAdapter<DevItemInfo, BaseViewHolder> {
//    public List<DevState> states = new ArrayList<>();
//
//    public DeviceRVAdapter() {
//        super(R.layout.item_device);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, DevItemInfo item) {
//        ALog.e("node_id-->" + item.node_id);
//        ALog.e("parent_node_id-->" + item.parent_node_id);
//        ALog.e("node_type-->" + item.node_type);
//        ALog.e("node_name-->" + item.node_name);
//        ALog.e("conn_mode-->" + item.conn_mode);
//        ALog.e("conn_params-->" + item.conn_params);
//        ALog.e("vendor_id-->" + item.vendor_id);
//        ALog.e("dev_id-->" + item.dev_id);
//        ALog.e("dev_type-->" + item.dev_type);
//        ALog.e("longitude-->" + item.longitude);
//        ALog.e("latitude-->" + item.latitude);
//        ALog.e("id_type-->" + item.id_type);
//        ALog.e("is_ptz-->" + item.is_ptz);
//        ALog.e("is_voc_talk-->" + item.is_voc_talk);
//        ALog.e("is_arming-->" + item.is_arming);
//        ALog.e("is_record-->" + item.is_record);
//        ALog.e("isSelectToPlay-->" + item.isSelectToPlay);
//        ALog.e("umid-->" + item.umid);
//        ALog.e("ip-->" + item.ip);
//        ALog.e("dev_user-->" + item.dev_user);
//        ALog.e("dev_passaword-->" + item.dev_passaword);
//        ALog.e("dev_ch_no-->" + item.dev_ch_no);
//        ALog.e("dev_ch_num-->" + item.dev_ch_num);
//        ALog.e("dev_stream_no-->" + item.dev_stream_no);
//        ALog.e("state-->" + states.get(helper.getLayoutPosition()).state);
//
//        helper.setText(R.id.tv_location_item_device, item.node_name)
//                .addOnClickListener(R.id.iv_setup_item_device)
//                .addOnClickListener(R.id.cv_item_device)
//                .addOnClickListener(R.id.iv_key_item_device);
//
//        if (states.size() >= helper.getLayoutPosition()) {
//            if (states.get(helper.getLayoutPosition()) != null) {
//                helper.setText(R.id.tv_online_item_device, states.get(helper.getLayoutPosition()).state == 1 ? "设备在线" : "设备离线");
//            }
//        }
//    }
//
//    public void setDeviceStates(List<DevState> states) {
//        this.states = states == null ? new ArrayList<DevState>() : states;
//    }
//}
