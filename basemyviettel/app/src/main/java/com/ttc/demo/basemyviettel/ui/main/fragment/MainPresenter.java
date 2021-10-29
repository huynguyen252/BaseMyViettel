package com.ttc.demo.basemyviettel.ui.main.fragment;

import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.utils.DialogUtils;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;
import com.ttc.demo.basemyviettel.data.model.GetShopHomeResult;

import retrofit2.Call;
import retrofit2.Response;

public class MainPresenter extends Presenter<MainContract.View, MainContract.Interactor>
        implements MainContract.Presenter {

    private ContainerView containerView;
    private MainContract.View mViewKhoSim;

    public MainPresenter(ContainerView containerView) {
        super(containerView);
        this.containerView = containerView;
    }

    public MainPresenter(MainContract.View mViewKhoSim) {
        this.mViewKhoSim = mViewKhoSim;
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
        public void getShopHomeResult() {
            NetWorkController.getShopHomeResult(new ViettelCallback<GetShopHomeResult>() {
                @Override
                public void onViettelSuccess(Call<GetShopHomeResult> call, Response<GetShopHomeResult> response) {
                    if (response.body() != null ){
                        mViewKhoSim.setShopHome(response.body());
                    }
                }

                @Override
                public void onViettelFailure(Call<GetShopHomeResult> call) {
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
