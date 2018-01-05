package com.meilun.security.smart.cateye.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meilun.security.smart.R;
import com.meilun.security.smart.common.Constants;
import com.meilun.security.smart.common.sdk.Utility;
import com.meilun.security.smart.entity.bean.MediaListItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.LocalFile;
import cn.itsite.abase.utils.ToastUtils;

/**
 * Author： Administrator on 2017/7/27 0027.
 * Email： liujia95me@126.com
 */
public class LocalImageFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private final int INIT_LIST = 8888;// 初始化列表
    private final int CREATE_FAILE = 8889;// 创建失败

    public List<MediaListItem> data = new ArrayList<>();
    private Handler handler;
    private boolean isImage = true;
    private boolean isEdit;

    Unbinder unbinder;
    private LocalImageRVAdapter adapter;

    public static LocalImageFragment newInstance(int fileType) {
        LocalImageFragment imageFragment = new LocalImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("fileType", fileType);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            isImage = args.getInt("fileType") == 0;
        }
        ALog.e(TAG,"isImage:"+isImage);
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
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("本地图片");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(adapter);
        handler = new MyHandler();
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            showLoading();
            new LoadDataThread().start();
        } else {
            ToastUtils.showToast(_mActivity, _mActivity.getResources().getString(R.string.NoSdcard));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void OpenMp4(String fileName) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        File f = new File(fileName);
        Uri uri = Uri.fromFile(f);
        it.setDataAndType(uri, "video/mp4");
        startActivity(it);

		/*
         * Intent intent = new Intent(AcMediaList.this, VideoPlayback.class);//
		 * 新添加的播放界面 intent.putExtra("fileName", fileName);
		 * System.out.println("发过去的的：：~~~！~" + fileName); startActivity(intent);
		 */

    }

    public void OpenImage(String fileName) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        File f = new File(fileName);
        Uri uri = Uri.fromFile(f);
        it.setDataAndType(uri, "image/*");
        startActivity(it);
    }

    public void AddPicture(Bitmap bmp, String fileName, String des)// 添加图片项
    {
        MediaListItem item = new MediaListItem();
        item.bmp = bmp;
        item.fileName = fileName;
        item.description = des;
        data.add(item);
    }

    /**
     * 加载图片列表
     */
    public void LoadMediaData() {
        try {
            List<String> files = null;
            if (isImage) {
                boolean b = LocalFile.CreateDirectory(Constants.UserImageDir);
                if (!b) {
                    handler.sendEmptyMessage(CREATE_FAILE);
                    ALog.e(TAG, "LoadMediaData 1111111111");
                    return;
                }
                files = LocalFile.GetMatchExtFiles(Constants.UserImageDir,
                        "jpeg");
                ALog.e(TAG, "LoadMediaData 2222222222:" + files.size());

            } else {
                boolean b = LocalFile.CreateDirectory(Constants.UserVideoDir);
                if (!b) {
                    handler.sendEmptyMessage(CREATE_FAILE);
                    ALog.e(TAG, "LoadMediaData 333333333333");
                    return;
                }
                files = LocalFile.GetMatchExtFiles(Constants.UserVideoDir,
                        "MP4");
                ALog.e(TAG, "LoadMediaData 444444444444");
            }
            for (int i = 0; i < files.size(); i++)// 将文件夹内的内容添加到列表
            {
                try {

                    String fileName = files.get(i);
                    String name = fileName
                            .substring(fileName.lastIndexOf("/") + 1);
                    Bitmap bmp = isImage ? BitmapFactory.decodeResource(
                            getResources(), R.drawable.ic_picture_black_120px)
                            : BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_picture_black_120px);
                    String des = name;
                    AddPicture(bmp, fileName, des);
                } catch (Error e) {
                    e.printStackTrace();
                }
            }
            ALog.e(TAG, "LoadMediaData 555555555555");
            Message msg = Message.obtain();
            msg.what = INIT_LIST;
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
            ALog.e(TAG, "LoadMediaData 6666666666666" + e);
            handler.sendEmptyMessage(CREATE_FAILE);
        }
    }

    class LoadDataThread extends Thread {
        @Override
        public void run() {
            LoadMediaData();
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == INIT_LIST) {
                if (data.size() == 0) {
                    ToastUtils.showToast(_mActivity, _mActivity.getResources().getString(R.string.none_files));
                }
                adapter = new LocalImageRVAdapter(data);

                adapter.setOnItemClickListener((adapter1, view, position) -> {
                    if (isImage) {
                        OpenImage(data.get(position).fileName);
                    } else {
                        OpenMp4(data.get(position).fileName);
                    }
                });
                dismissLoading();
                if (isImage){
                    new ImageLoadTask().execute();// 执行加载图片的任务
                }
            } else if (msg.what == CREATE_FAILE) {
                ToastUtils.showToast(_mActivity, "无法创建访问SD卡内容");
                dismissLoading();
            }

			/*
			 * isSelected=new int[MediaAdapter.list.size()]; for (int i = 0; i <
			 * isSelected.length; i++) { isSelected[i]=-1; }
			 */
            super.handleMessage(msg);
        }
    }

    public class ImageLoadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < data.size(); i++) {
                MediaListItem item = data.get(i);
                item.bmp = Utility.GetThumbImage(item.fileName, 50, 50);
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyDataSetChanged();// 更新UI
        }

    }

}
