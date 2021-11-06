package com.ttc.demo.basemyviettel.ui.main.fragment;

import android.app.Activity;
import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVThemeProductResponse;
import com.ttc.demo.basemyviettel.ui.main.model.sim.ShopHomeResponse;
import com.ttc.demo.basemyviettel.utils.DialogUtils;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;

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
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Response;

public class MainPresenter extends Presenter<MainContract.View, MainContract.Interactor>
        implements MainContract.Presenter {

    private ContainerView containerView;
    private
    Activity context;
    private MainContract.View mViewShopHome;
    private
    CompositeDisposable disposable = new CompositeDisposable();

    public MainPresenter(ContainerView containerView) {
        super(containerView);
        this.containerView = containerView;
    }

    public
    MainPresenter(Activity context, MainContract.View mViewShopHome) {
        this.context = context;
        this.mViewShopHome = mViewShopHome;
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
    public
    void getAllCategoryShopHome(String limit) {
        DialogUtils.showProgressDialog(context);
        Observable<ShopHomeResponse> observable1 = NetWorkController.getApiBuilderRxJava().getShopHome1();
        Observable<MVThemeProductResponse> observable2 = NetWorkController.getApiBuilderRxJava().getThemeProduct1(limit);
        disposable.add(Observable.zip(observable1, observable2, (shopHomeResponse, mvThemeProductResponse) -> {
            List<Object> list = new ArrayList<>();
            list.add(shopHomeResponse);
            list.add(mvThemeProductResponse);
            return list;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(objects -> {
                    if (objects!= null){
                        mViewShopHome.setAllCategoryShopHome(objects);
                    }
                    else {
                        mViewShopHome.setAllCategoryShopHome(null);
                    }
                }, throwable -> {Log.e("Failure", throwable.getLocalizedMessage());}));
    }

    @Override
    public void onDestroyView() {
        disposable.dispose();
    }
}
