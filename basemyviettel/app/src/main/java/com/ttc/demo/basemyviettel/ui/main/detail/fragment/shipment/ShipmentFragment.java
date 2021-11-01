package com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;

public
class ShipmentFragment extends ViewFragment<ShipmentContract.Presenter> implements ShipmentContract.View {
    public static ShipmentFragment getInstance(){
        return new ShipmentFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_shipment;
    }
}
