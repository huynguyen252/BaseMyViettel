package com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment;

import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.ui.main.detail.MVShopResultActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStateDataLitener;

public
class PaymentPresenter extends Presenter<PaymentContract.View, PaymentContract.Interactor> implements PaymentContract.Presenter, OnStateDataLitener{
    //private NumberModel numberST;
    private
    OnStateDataLitener litener;

    private PaymentContract.View viewPayment;
    public
    PaymentPresenter(ContainerView containerView) {
        super(containerView);
    }


    public
    PaymentPresenter(PaymentContract.View mView) {
        this.viewPayment = mView;
    }

    @Override
    public
    void start() {
        mView.initLayout();
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

    @Override
    public
    void getDataReload() {

    }

    @Override
    public
    void onStateData(NumberModel numberModel) {
        if(numberModel != null){
            Log.d("numberST", numberModel.getIsdn());
        }
        Log.d("numberST", "null");
    }
}
