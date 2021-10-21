package com.ttc.demo.basemyviettel.ui.main.fragment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.utils.DialogUtils;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;

import retrofit2.Call;
import retrofit2.Response;

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

//        RxJava
//        DisposableManager.add(NetWorkController.getApiBuilderRxJava().getCommonSetting(token)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(getCommonSettingResult -> {
//                    mView.setInformation(getCommonSettingResult);
//                }, Throwable::printStackTrace));

//      Retrofit2
        mInteractor.getCommonSetting("", new ViettelCallback<GetCommonSettingResult>() {
            @Override
            public void onViettelSuccess(Call<GetCommonSettingResult> call, Response<GetCommonSettingResult> response) {
                DialogUtils.dismissProgressDialog();
                mView.setInformation(response.body());
            }

            @Override
            public void onViettelFailure(Call<GetCommonSettingResult> call) {
                DialogUtils.dismissProgressDialog();
            }

        });
    }

    @Override
    public void onDestroyView() {
        //RxJava
        //DisposableManager.dispose();
    }

}
