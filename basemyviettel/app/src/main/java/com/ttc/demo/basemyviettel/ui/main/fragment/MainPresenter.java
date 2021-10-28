package com.ttc.demo.basemyviettel.ui.main.fragment;

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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Response;

public class MainPresenter extends Presenter<MainContract.View, MainContract.Interactor>
        implements MainContract.Presenter {

    private ContainerView containerView;
    private MainContract.View mViewShopHome;

    public MainPresenter(ContainerView containerView) {
        super(containerView);
        this.containerView = containerView;
    }

    public
    MainPresenter(MainContract.View mViewShopHome) {
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

//    @Override
//    public
//    void getShopHomeResult() {
//        NetWorkController.getShopHome(new ViettelCallback<ShopHomeResponse>() {
//            @Override
//            public
//            void onViettelSuccess(Call<ShopHomeResponse> call, Response<ShopHomeResponse> response) {
//                if (response.body() != null ){
//                    mViewShopHome.setShopHome(response.body());
//                }
//            }
//
//            @Override
//            public
//            void onViettelFailure(Call<ShopHomeResponse> call) {
//                Log.d("callShopHome", "errprs");
//            }
//        });
//    }

//    @Override
//    public
//    void getThemeProductResult(String limit) {
//        NetWorkController.getThemeProduct(limit, new ViettelCallback<MVThemeProductResponse>() {
//            @Override
//            public
//            void onViettelSuccess(Call<MVThemeProductResponse> call, Response<MVThemeProductResponse> response) {
//                if(response.body() != null){
//                    mViewShopHome.setThemeProduct(response.body());
//                }
//            }
//
//            @Override
//            public
//            void onViettelFailure(Call<MVThemeProductResponse> call) {
//
//            }
//        });
//    }

    @Override
    public
    void getAllCategoryShopHome(String limit) {
        Observable<ShopHomeResponse> observable1 = NetWorkController.getApiBuilderRxJava().getShopHome1();
        Observable<MVThemeProductResponse> observable2 = NetWorkController.getApiBuilderRxJava().getThemeProduct1(limit);
        Observable.zip(observable1, observable2, (shopHomeResponse, mvThemeProductResponse) -> {
                    List<Object> list = new ArrayList<>();
                    list.add(shopHomeResponse);
                    list.add(mvThemeProductResponse);
                    return list;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Object>>() {
                    @Override
                    public
                    void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public
                    void onNext(@NonNull List<Object> objects) {
                        mViewShopHome.setAllCategoryShopHome(objects);
                    }

                    @Override
                    public
                    void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public
                    void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        //RxJava
        //DisposableManager.dispose();
    }
}
