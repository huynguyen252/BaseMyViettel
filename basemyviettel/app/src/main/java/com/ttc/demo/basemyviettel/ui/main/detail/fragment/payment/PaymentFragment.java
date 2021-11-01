package com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;

public
class PaymentFragment extends ViewFragment<PaymentContract.Presenter> implements PaymentContract.View{
    public static PaymentFragment getInstance(){
        return new PaymentFragment();
    }
    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_payment;
    }
}
