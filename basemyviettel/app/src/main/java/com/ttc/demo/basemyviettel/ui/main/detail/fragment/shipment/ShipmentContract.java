package com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ShopAreaModel;

import java.util.List;

public
interface ShipmentContract {
    interface Interactor extends IInteractor<Presenter> {}
    interface View extends PresentView<Presenter> {
        void setListShopAreaByCode(List<ShopAreaModel> list);
        void setListArea(List<Object> list);
    }
    interface Presenter extends IPresenter<View, Interactor> {
        void getListShopAreaByCode(String areaCode);
        void getListArea();
    }
}
