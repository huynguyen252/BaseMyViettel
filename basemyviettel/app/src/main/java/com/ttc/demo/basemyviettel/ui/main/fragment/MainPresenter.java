package com.ttc.demo.basemyviettel.ui.main.fragment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * The ChangeAccount Presenter
 */
public class MainPresenter extends Presenter<MainContract.View, MainContract.Interactor>
        implements MainContract.Presenter {

    private ContainerView containerView;

    public MainPresenter(ContainerView containerView) {
        super(containerView);
        this.containerView = containerView;
    }

    @Override
    public MainContract.View onCreateView() {
        return MainFragment.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public MainContract.Interactor onCreateInteractor() {
        return new MainInteractor(this);
    }

    @Override
    public void getCommonSetting(String token) {
        DialogUtils.showProgressDialog(containerView.getViewContext());
        mInteractor.getCommonSetting(token, new Observer<GetCommonSettingResult>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull GetCommonSettingResult getCommonSettingResult) {
                DialogUtils.dismissProgressDialog();
                mView.setInformation(getCommonSettingResult);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
