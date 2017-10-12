package com.aglhz.s1.net.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.net.contract.NetContract;
import com.aglhz.s1.net.model.NetModel;

import org.json.JSONArray;
import org.json.JSONException;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class NetPresenter extends BasePresenter<NetContract.View, NetContract.Model> implements NetContract.Presenter {
    private static final String TAG = NetPresenter.class.getSimpleName();

    public NetPresenter(NetContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected NetContract.Model createModel() {
        return new NetModel();
    }

    @Override
    public void command(int cmd, String params) {
        mModel.command(cmd, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void _onNext(String s) {
                        ALog.e("指令返回-->" + s);
                        JSONArray root = null;
                        try {
                            root = new JSONArray(s);
                        } catch (JSONException e) {
                            getView().error("数据解析异常！");
                            e.printStackTrace();
                        }
                        getView().command(cmd, root);
                    }
                });
    }
}