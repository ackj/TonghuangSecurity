package com.aglhz.s1.room.presenter;

import android.support.annotation.NonNull;

import com.aglhz.s1.common.Params;
import com.aglhz.s1.room.contract.RoomContract;
import com.aglhz.s1.room.model.RoomModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
public class RoomPresenter extends BasePresenter<RoomContract.View, RoomContract.Model> implements RoomContract.Presenter {
    private final String TAG = RoomPresenter.class.getSimpleName();

    public RoomPresenter(RoomContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RoomContract.Model createModel() {
        return new RoomModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestRoomInfo(Params params) {
        mRxManager.add(mModel.requestRoomInfo(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
//                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
//                        getView().responseRoomInfo(bean);
//                    } else {
//                        getView().error(bean.getOther().getMessage());
//                    }
                }, this::error));
    }

    
}