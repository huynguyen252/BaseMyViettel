package com.ttc.demo.basemyviettel.ui.main.detail;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.ui.base.ViettelBaseActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.simstore.NewSimPresenter;

public
class MVShopResultActivity extends ViettelBaseActivity {
    @Override
    public
    ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new NewSimPresenter(this).getFragment();    }
}
