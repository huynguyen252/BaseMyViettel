package com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment;

import com.gemvietnam.base.viper.Interactor;

public
class ShipmentInteractor extends Interactor<ShipmentContract.Presenter> implements ShipmentContract.Interactor {
    public
    ShipmentInteractor(ShipmentContract.Presenter presenter) {
        super(presenter);
    }
}
