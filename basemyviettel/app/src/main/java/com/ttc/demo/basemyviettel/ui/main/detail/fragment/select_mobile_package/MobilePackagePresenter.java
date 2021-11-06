package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package;

import android.app.Activity;
import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim.SelectSimFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.response.MobilePackageResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.ServicePackageResponse;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public
class MobilePackagePresenter extends Presenter<MobilePackageContract.View, MobilePackageContract.Interactor> implements MobilePackageContract.Presenter{
    private
    Activity context;
    private MobilePackageContract.View viewMobilePackage;
    private OnStepDoneListener listener;
    private
    CompositeDisposable disposable = new CompositeDisposable();

    public
    MobilePackagePresenter(ContainerView containerView) {
        super(containerView);
    }

    public
    MobilePackagePresenter(Activity context, MobilePackageContract.View viewMobilePackage) {
        this.context = context;
        this.viewMobilePackage = viewMobilePackage;
    }

    @Override
    public
    void start() {
        mView.initLayout();
    }

    @Override
    public
    MobilePackageContract.Interactor onCreateInteractor() {
        return new MobilePackageInteractor(this);
    }

    @Override
    public
    MobilePackageContract.View onCreateView() {
        return MobilePackageFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {
        disposable.dispose();
    }

    @Override
    public
    void getDataPackage() {
        DialogUtils.showProgressDialog(context);
        Observable<MobilePackageResponse> observable = NetWorkController.getApiBuilderRxJava().getListMobilePackage();
        Observable<ServicePackageResponse> observable1 = NetWorkController.getApiBuilderRxJava().getListVasPackage();

        disposable.add(Observable.zip(observable, observable1, (mobilePackageResponse, servicePackageResponse) -> {
            List<Object> list = new ArrayList<>();
            list.add(mobilePackageResponse);
            list.add(servicePackageResponse);
            return list;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(objects -> viewMobilePackage.setDataPackage(objects)
                        , throwable -> {Log.e("Failure", throwable.getLocalizedMessage());}));
    }

    @Override
    public
    MobilePackagePresenter setSelectedMobilePackageListener(OnStepDoneListener listener) {
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
    void onStepMobilePackageDone() {
        if(listener != null){
            listener.onStepSelectMobilePackageDone();
        }
    }
}
