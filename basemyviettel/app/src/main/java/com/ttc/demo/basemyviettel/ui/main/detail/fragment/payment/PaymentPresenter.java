package com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

public
class PaymentPresenter extends Presenter<PaymentContract.View, PaymentContract.Interactor> implements PaymentContract.Presenter{
    public
    PaymentPresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public
    void start() {

    }

    @Override
    public
    PaymentContract.Interactor onCreateInteractor() {
        return new PaymentInteractor(this);
    }

    @Override
    public
    PaymentContract.View onCreateView() {
        return PaymentFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {

    }
}
