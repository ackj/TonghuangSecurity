package com.meilun.security.smart.cateye.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Player.Core.PlayerDownFileCore;
import com.Player.Source.TDownFrame;
import com.Player.Source.TVideoFile;
import com.meilun.security.smart.R;
import com.meilun.security.smart.common.Constants;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.LocalFile;
import cn.itsite.abase.utils.ToastUtils;

/**
 * Author: LiuJia on 2017/5/4 0004 11:18.
 * Email: liujia95me@126.com
 */

public class RecordFragment extends BaseFragment {
    public static final String TAG = RecordFragment.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    Unbinder unbinder;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RecordRVAdapter adapter;

    public static RecordFragment newInstance() {
        return new RecordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cateye_record, container, false);
        unbinder = ButterKnife.bind(this, view);
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
        toolbarTitle.setText(QueryFragment.devId);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new RecordRVAdapter(QueryFragment.data);
        recyclerview.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            try {
                startDown((TVideoFile) adapter1.getData().get(position));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            dismissLoading();
            switch (msg.what) {
                case 1:
                    ToastUtils.showToast(_mActivity, "下载完成");
                    String filePath = (String) msg.obj;
                    OpenDownFile(filePath);
                    break;
                case -1:
                    ToastUtils.showToast(_mActivity, "文件下载失败");
                    break;
                case -2:
                    ToastUtils.showToast(_mActivity, "文件创建失败");
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    /**
     * 启动文件下载
     *
     * @param tVideoFile
     * @throws IOException
     */
    public void startDown(final TVideoFile tVideoFile) throws IOException {
        boolean b = LocalFile.CreateDirectory(Constants.UserVideoDir);
        if (b == false) {
            return;
        }
        String filename = tVideoFile.FileName.replace('/', '_');
        final String fileptah = Constants.UserVideoDir + "/" + filename;
        File file = new File(fileptah);
        if (file.exists()) {
            if (file.length() != 0) {
                OpenDownFile(fileptah);
                return;
            } else
                file.delete();

        }
        file.createNewFile();
        showLoading();
        new DownThread(tVideoFile, file).start();
    }

    public class DownThread extends Thread {
        TVideoFile tVideoFile;
        private File fileptah;

        public DownThread(TVideoFile tVideoFile, File fileptah) {
            super();
            this.tVideoFile = tVideoFile;
            this.fileptah = fileptah;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub

            PlayerDownFileCore playerDownFileCore = null;
            try {
                DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream(fileptah)));

                // TODO Auto-generated method stub 下载类 传入设备umid，用户名，密码
                playerDownFileCore = new PlayerDownFileCore(QueryFragment.devId, QueryFragment.devUserName, QueryFragment.devUserPwd);
                // 传入需要下载的文件名，大小
                int ret = playerDownFileCore.StartDownFileByUMID(
                        tVideoFile.FileName, tVideoFile.nFileSize);
                if (ret != 0) {
                    int trydowntime = 0;
                    int DownOver = 0;// 结束标识
                    try {
                        while (DownOver != 1) {
                            TDownFrame mFrame = playerDownFileCore
                                    .GetDownFileData();
                            int currentPosition = playerDownFileCore
                                    .GetDownPos();
                            DownOver = mFrame.DownOver;
                            if (mFrame != null && mFrame.iData != null) {
                                if (mFrame.iData.length != 0) {
                                    trydowntime = 0;
                                    out.write(mFrame.iData);
                                    Log.d("GetDownFileData",
                                            "GetDownFileData size:"
                                                    + mFrame.iData.length
                                                    + ",下载进度："
                                                    + currentPosition + "%"
                                                    + ",结束标志："
                                                    + mFrame.DownOver);
                                } else {
                                    Thread.sleep(100);

                                    Log.d("trydowntime", "trydowntime: "
                                            + trydowntime);
                                    trydowntime++;
                                }

                            } else {
                                Thread.sleep(100);
                                Log.d("trydowntime", "trydowntime: "
                                        + trydowntime);
                                trydowntime++;
                            }
                            if (trydowntime > 100) {
                                out.flush();
                                out.close();
                                fileptah.delete();
                                handler.sendEmptyMessage(-1);
                                return;
                            }
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    out.flush();
                    out.close();

                    handler.sendMessage(Message.obtain(handler, 1,
                            fileptah.getAbsolutePath()));
                } else {
                    handler.sendEmptyMessage(-1);
                    fileptah.delete();
                }

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                handler.sendEmptyMessage(-2);
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                handler.sendEmptyMessage(-3);
                e.printStackTrace();
            }
            if (playerDownFileCore != null) {
                playerDownFileCore.StopDownFile();
            }

            super.run();
        }
    }

    public void OpenDownFile(String fileName) {
        if (fileName.endsWith(".3gp")) {
            Intent it = new Intent(Intent.ACTION_VIEW);
            File f = new File(fileName);
            Uri uri = Uri.fromFile(f);
            it.setDataAndType(uri, "video/3gp");
            startActivity(it);
        } else if (fileName.endsWith(".jpg")) {
            Intent it = new Intent(Intent.ACTION_VIEW);
            File f = new File(fileName);
            Uri uri = Uri.fromFile(f);
            it.setDataAndType(uri, "image/*");
            startActivity(it);
        }

    }

}
