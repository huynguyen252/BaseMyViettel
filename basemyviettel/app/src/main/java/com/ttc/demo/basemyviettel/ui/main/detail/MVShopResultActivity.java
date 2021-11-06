package com.ttc.demo.basemyviettel.ui.main.detail;

import android.os.Bundle;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.ui.base.ViettelBaseActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;
import com.ttc.demo.basemyviettel.ui.main.detail.simstore.NewSimPresenter;

import java.util.HashMap;
import java.util.Map;

public
class MVShopResultActivity extends ViettelBaseActivity {

    public static
    Map<String, Object> orderState = new HashMap<String, Object>();
    public static final String STEP_NUMBER = "number selected";
    public static final String STEP_NUMBER_TYPE = "number type selected";
    public static final String STEP_PACKAGE_MOBILE = "mobile package selected";
    public static final String STEP_PACKAGE_VAS = "service package selected";
    public static final String STEP_PACKAGE_MONEY_CARD = "card if prepay";
    public static final String STEP_INFO_CUSTOMER = "infomation customer";
    @Override
    public
    ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new NewSimPresenter(this).getFragment();
    }

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderState.put(STEP_NUMBER, null);
        orderState.put(STEP_NUMBER_TYPE, null);
        orderState.put(STEP_PACKAGE_MOBILE, null);
        orderState.put(STEP_PACKAGE_VAS, null);
        orderState.put(STEP_PACKAGE_MONEY_CARD, null);
        orderState.put(STEP_INFO_CUSTOMER, null);
    }
}
