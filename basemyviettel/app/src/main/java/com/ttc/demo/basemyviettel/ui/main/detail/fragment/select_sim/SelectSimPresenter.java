package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import android.app.Activity;
import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.data.model.SimpleResult;
import com.ttc.demo.basemyviettel.ui.main.detail.response.NumberResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.simstore.NewSimPresenter;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public
class SelectSimPresenter extends Presenter<SelectSimContract.View, SelectSimContract.Interactor> implements SelectSimContract.Presenter {
    private
    Activity context;
    private SelectSimContract.View searchSimView;
    private OnStepDoneListener listener;
    private CompositeDisposable disposable = new CompositeDisposable();

    public
    SelectSimPresenter(ContainerView containerView) {
        super(containerView);
    }

    public
    SelectSimPresenter(Activity context, SelectSimContract.View searchSimView) {
        this.context = context;
        this.searchSimView = searchSimView;
    }

    @Override
    public
    void start() {
        mView.initLayout();
    }

    @Override
    public
    SelectSimContract.Interactor onCreateInteractor() {
        return new SelectSimInteractor(this);
    }

    @Override
    public
    SelectSimContract.View onCreateView() {
        return SelectSimFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {
        disposable.dispose();
    }

    @Override
    public
    void getListNumber() {
        DialogUtils.showProgressDialog(context);
        Observable<NumberResponse> observable = NetWorkController.getApiBuilderRxJava().getListNumber();
        disposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(numberResponse -> searchSimView.setListNumber(numberResponse.getData())
                        , throwable -> {Log.e("Failure", throwable.getLocalizedMessage());}));

    }

    @Override
    public
    void saveBlockSimInfo() {
        //call api saveBlockSimInfo save state
        mView.saveBlockSimInfoSuccess();
    }

    @Override
    public
    SelectSimPresenter setSelectSimListener(OnStepDoneListener listener) {
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
    void onStepSelectMobilePackage() {
        if(listener != null){
            listener.onStepSelectSimDone();
        }
    }

}
