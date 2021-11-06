package com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment;

import android.app.Activity;
import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaDetailResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.ShopAreaResponse;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public
class ShipmentPresenter extends Presenter<ShipmentContract.View, ShipmentContract.Interactor> implements ShipmentContract.Presenter{
    private
    Activity context;
    private ShipmentContract.View viewShipment;
    private
    CompositeDisposable disposable = new CompositeDisposable();
    public
    ShipmentPresenter(ContainerView containerView) {
        super(containerView);
    }

    public
    ShipmentPresenter(Activity context, ShipmentContract.View viewShipment) {
        this.context = context;
        this.viewShipment = viewShipment;
    }

    @Override
    public
    void start() {

    }

    @Override
    public
    ShipmentContract.Interactor onCreateInteractor() {
        return new ShipmentInteractor(this);
    }

    @Override
    public
    ShipmentContract.View onCreateView() {
        return ShipmentFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {
        disposable.dispose();
    }

    @Override
    public
    void getListShopAreaByCode(String areaCode) {
        DialogUtils.showProgressDialog(context);
        Observable<ShopAreaResponse> data = NetWorkController.getApiBuilderRxJavaURLMyVT().getShopAreaByCode(areaCode);
        disposable.add(data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopAreaResponse>() {
                    @Override
                    public
                    void accept(ShopAreaResponse shopAreaResponse) throws Exception {
                        viewShipment.setListShopAreaByCode(shopAreaResponse.getData());
                        Log.d("callByCode", "accept: ");
                    }
                }));
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
                    viewShipment.setListArea(objects);
                }));
    }
}
