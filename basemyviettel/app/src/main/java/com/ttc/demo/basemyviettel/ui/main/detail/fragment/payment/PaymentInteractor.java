package com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment;

import com.gemvietnam.base.viper.Interactor;

public
class PaymentInteractor extends Interactor<PaymentContract.Presenter> implements PaymentContract.Interactor{
    public
    PaymentInteractor(PaymentContract.Presenter presenter) {
        super(presenter);
    }
}
