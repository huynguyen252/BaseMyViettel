package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info;

import android.app.Activity;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaModel;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaDetailResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaResponse;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public
class InfoPresenter extends Presenter<InfoContract.View, InfoContract.Interactor> implements InfoContract.Presenter{
    private
    Activity context;
    private InfoContract.View viewInfo;
    private OnStepDoneListener listener;
    private
    CompositeDisposable disposable = new CompositeDisposable();

    public
    InfoPresenter(Activity context, InfoContract.View viewInfo) {
        this.context = context;
        this.viewInfo = viewInfo;
    }

    public
    InfoPresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public
    void start() {
        mView.initLayout();
    }

    @Override
    public
    InfoContract.Interactor onCreateInteractor() {
        return new InfoInteractor(this);
    }

    @Override
    public
    InfoContract.View onCreateView() {
        return InfoFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {
        disposable.dispose();
    }

    @Override
    public
    void getListArea() {
        DialogUtils.showProgressDialog(context);
        Observable<AreaResponse> areas = NetWorkController.getApiBuilderRxJava().getListArea();
        Observable<AreaDetailResponse> provincesDetail = NetWorkController.getApiBuilderRxJava().getListProvinceDetail();

        disposable.add(Observable.zip(areas, provincesDetail, (areaResponse, areaDetailResponse) -> {
            List<Object> list = new ArrayList<>();
            list.add(areaResponse);
            list.add(areaDetailResponse);
            return list;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(objects -> {
                    viewInfo.setListArea(objects);
                }));
    }

    @Override
    public
    InfoPresenter setSelectInfoListener(OnStepDoneListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public
    OnStepDoneListener getListener() {
        return listener;
    }

    @Override
    public
    void onStepEnterInfoDone() {
        if(listener!=null){
            listener.onStepEnterInfoDone();
        }
    }
}
