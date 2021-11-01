package com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

public
interface PaymentContract {
    interface Interactor extends IInteractor<Presenter> {}
    interface View extends PresentView<Presenter> {}
    interface Presenter extends IPresenter<View, Interactor> {}
}
