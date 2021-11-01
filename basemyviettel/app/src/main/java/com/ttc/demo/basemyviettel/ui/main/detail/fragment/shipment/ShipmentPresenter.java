package com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

public
class ShipmentPresenter extends Presenter<ShipmentContract.View, ShipmentContract.Interactor> implements ShipmentContract.Presenter{
    public
    ShipmentPresenter(ContainerView containerView) {
        super(containerView);
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

    }
}
