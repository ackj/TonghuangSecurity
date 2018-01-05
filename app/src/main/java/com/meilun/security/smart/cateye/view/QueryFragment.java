package com.meilun.security.smart.cateye.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Player.Core.PlayerSearchCore;
import com.Player.Source.Date_Time;
import com.Player.Source.TVideoFile;
import com.Player.web.response.DevItemInfo;
import com.meilun.security.smart.App;
import com.meilun.security.smart.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.common.RxManager;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/4 0004 18:45.
 * Email: liujia95me@126.com
 */

public class QueryFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = QueryFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_fragment_query)
    RecyclerView recyclerview;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    private QueryRVAdapter adapter;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private TextView tvFileType;
    private DevItemInfo devItemInfo;
    Calendar endCalendar = Calendar.getInstance();
    Calendar startCalendar = Calendar.getInstance();
    Date_Time endDateTime = new Date_Time();
    Date_Time startDateTime = new Date_Time();
    private RxManager mRxManager = new RxManager();

    public static List<TVideoFile> data = new ArrayList<>();
    private int recordType = PlayerSearchCore.NPC_D_MON_REC_FILE_TYPE_ALL;

    private String[] selectDataSourcesArr = {"本地", "猫眼"};
    private String[] selectFileTypeArr = {"图片", "录像"};
    private int dataSourcesWhich;
    private int fileTypeWhich;

    private Unbinder unbinder;
    private TextView tvDataSources;

    public static QueryFragment newInstance() {
        return new QueryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cateye_query, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
        toolbarTitle.setText("记录查询");
        toolbarMenu.setText("查询");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new QueryRVAdapter(App.devices);
        View view = LayoutInflater.from(_mActivity).inflate(R.layout.item_cateye_query_header, null, false);
        tvStartDate = (TextView) view.findViewById(R.id.tv_start_date);
        tvEndDate = (TextView) view.findViewById(R.id.tv_end_date);
        tvFileType = (TextView) view.findViewById(R.id.tv_file_type);
        tvDataSources = (TextView) view.findViewById(R.id.tv_data_sources);
        view.findViewById(R.id.ll_start_date).setOnClickListener(this);
        view.findViewById(R.id.ll_end_date).setOnClickListener(this);
        view.findViewById(R.id.ll_file_type).setOnClickListener(this);
        view.findViewById(R.id.ll_data_sources).setOnClickListener(this);
        adapter.addHeaderView(view);
        recyclerview.setAdapter(adapter);
        //initTime
        startCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH) - 1);
        tvStartDate.setText(startCalendar.get(Calendar.YEAR) + "-" + (startCalendar.get(Calendar.MONTH) + 1) + "-" + startCalendar.get(Calendar.DAY_OF_MONTH));
        changevalue(startCalendar, startDateTime);

        tvEndDate.setText(endCalendar.get(Calendar.YEAR) + "-" + (endCalendar.get(Calendar.MONTH) + 1) + "-" + endCalendar.get(Calendar.DAY_OF_MONTH));
        changevalue(endCalendar, endDateTime);
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            devItemInfo = (DevItemInfo) adapter1.getData().get(position);
            ALog.e(TAG, "select devItemInfo :" + devItemInfo.node_name + " dev_id:" + devItemInfo.dev_id + " node_id:" + devItemInfo.node_id);
            adapter.setSelectedItem(position);
            parseParams();
        });
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(DeviceEvent event) {
//        ALog.e(TAG, "DeviceEvent:" + App.devices.size());
//        adapter.setNewData(App.devices);
//        if (App.devices.size() > 0) {
//            devItemInfo = App.devices.get(0);
//            parseParams();
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.toolbar_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_menu:
                if (dataSourcesWhich == 0) {
                    //本地
                    ALog.e(TAG, "fileTypeWhich:" + fileTypeWhich);
                    _mActivity.start(LocalImageFragment.newInstance(fileTypeWhich));
                } else {
                    search();
                }
                ALog.e(TAG, "dataSourcesWhich:" + dataSourcesWhich);
                break;
        }
    }

    private String[] params;
    public static String devUserName;
    public static String devUserPwd;
    public static String devId;

    public void search() {
        mRxManager.add(Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> e) {
                        ALog.e(TAG, "search:1111111111");
                        PlayerSearchCore SearchCore = new PlayerSearchCore(_mActivity);
                        // int ret = SearchCore.SearchRecFileEx(deviceId, startTime, endTime,
                        // recordType);// 18100000返回1或-1
                        ALog.e(TAG, "查找设备号：id:" + devId + " name:" + devUserName + " pwd:" + devUserPwd + "(" + startDateTime.hour
                                + ":" + startDateTime.minute + "--" + endDateTime.hour + ":"
                                + endDateTime.minute + ")" + ",recordType:" + recordType);
                        int ret = SearchCore.SearchRecFileEx(devId, devUserName, devUserPwd, startDateTime, endDateTime, 65280);

                        //
                        // int ret =
                        // SearchCore.SearchRecFileEx(2060,"192.168.10.247",34567,BaseConstants.user,
                        // BaseConstants.password, 0,1,startTime, endTime, recordType);

                        if (ret > 0) {
                            // TVideoFile videofile1;
                            while (true) {
                                TVideoFile videofile = SearchCore.GetNextRecFile(); // 获取录像文件
                                if (videofile == null) {
                                    ALog.e(TAG, "查找结点结束");
                                    break;
                                } else {
                                    ALog.e(TAG, "查找到：" + videofile.FileName);
                                    data.add(Copy(videofile));
                                }
                            }
                        }
                        if (ret > 0) {
                            e.onNext(ret);
                        } else {
                            e.onError(new Exception());
                        }
                        SearchCore.Release();
                        e.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            _mActivity.start(RecordFragment.newInstance());
                            DialogHelper.successSnackbar(getView(), "搜索成功");
                        }, throwable -> {
                            DialogHelper.errorSnackbar(getView(), "搜索失败");
                            dismissLoading();
                        }, this::dismissLoading/*, disposable -> showLoading()*/)
        );
    }

    public TVideoFile Copy(TVideoFile v1) {
        TVideoFile v2 = new TVideoFile();
        v2.Channel = v1.Channel;
        v2.eday = v1.eday;
        v2.ehour = v1.ehour;
        v2.eminute = v1.eminute;
        v2.emonth = v1.emonth;
        v2.eminute = v1.eminute;
        v2.esecond = v1.esecond;
        v2.eyear = v1.eyear;
        v2.FileName = v1.FileName;
        v2.nFileSize = v1.nFileSize;
        v2.nFileType = v1.nFileType;
        v2.nParam1 = v1.nParam1;
        v2.nParam2 = v1.nParam2;
        v2.sday = v1.sday;
        v2.shour = v1.shour;
        v2.sminute = v1.sminute;
        v2.smonth = v1.smonth;
        v2.ssecond = v1.ssecond;
        v2.syear = v1.syear;
        return v2;
    }

    //解析设备参数，如果已经解析过，再次点击播放的时候就不需要解析。
    private void parseParams() {
        if (devItemInfo == null) {
            DialogHelper.errorSnackbar(getView(), "找不到猫眼信息！");
            return;
        }

        ALog.e(TAG, "parseParams device.conn_params-->" + devItemInfo.conn_params);
        //解析参数
        params = devItemInfo.conn_params.split(",");
        devUserName = "";
        devId = "";
        devUserPwd = "";

        for (int i = 0; i < params.length; i++) {
            if (params[i].contains("UserName")) {
                devUserName = params[i].split("=")[1];
                continue;
            }

            if (params[i].contains("UserPwd")) {
                devUserPwd = params[i].split("=")[1];
                continue;
            }

            if (params[i].contains("DevId")) {
                devId = params[i].split("=")[1];
            }
        }

        ALog.e(TAG, "parseParams dev name:-->" + devUserName);
        ALog.e(TAG, "parseParams dev id:-->" + devId);
        devUserPwd = "admin";
        ALog.e(TAG, "parseParams dev pwd:-->" + devUserPwd);
    }

    public void selectTime(TextView tv, Calendar calendar, Date_Time dateTime) {
        DatePickerDialog endDateDialog = new DatePickerDialog(_mActivity, (view, year, month, dayOfMonth) -> {
            tv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            calendar.set(year, month, dayOfMonth);
            changevalue(calendar, dateTime);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        endDateDialog.show();
    }

    public void changevalue(Calendar calendar, Date_Time dateTime) {
        dateTime.year = ((short) calendar.get(Calendar.YEAR));
        dateTime.month = ((short) (calendar.get(Calendar.MONTH) + 1));
        dateTime.day = ((byte) calendar.get(Calendar.DAY_OF_MONTH));
        dateTime.hour = ((byte) calendar.get(Calendar.HOUR_OF_DAY));
        dateTime.minute = ((byte) calendar.get(Calendar.MINUTE));
        dateTime.second = ((byte) calendar.get(Calendar.SECOND));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_start_date:
                selectTime(tvStartDate, startCalendar, startDateTime);
                break;
            case R.id.ll_end_date:
                selectTime(tvEndDate, endCalendar, endDateTime);
                break;
            case R.id.ll_file_type:
                new AlertDialog.Builder(_mActivity)
                        .setItems(selectFileTypeArr, (dialog, which) -> {
                            tvFileType.setText(selectFileTypeArr[fileTypeWhich = which]);
                        }).show();
                break;
            case R.id.ll_data_sources:
                new AlertDialog.Builder(_mActivity)
                        .setItems(selectDataSourcesArr, (dialog, which) -> {
                            tvDataSources.setText(selectDataSourcesArr[dataSourcesWhich = which]);
                        }).show();
                break;
        }
    }
}
