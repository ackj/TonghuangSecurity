package com.aglhz.s1.more.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.more.contract.RoomManagerContract;
import com.aglhz.s1.more.model.RoomManagerModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
public class RoomManagerPresenter extends BasePresenter<RoomManagerContract.View, RoomManagerContract.Model> implements RoomManagerContract.Presenter {
    private final String TAG = RoomManagerPresenter.class.getSimpleName();

    public RoomManagerPresenter(RoomManagerContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RoomManagerContract.Model createModel() {
        return new RoomManagerModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestHouseList(Params params) {
        mRxManager.add(mModel.requestHouseList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseHouseList(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }

    
}