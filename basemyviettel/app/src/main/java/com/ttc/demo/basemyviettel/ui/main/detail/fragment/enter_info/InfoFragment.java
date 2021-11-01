package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;

public
class InfoFragment extends ViewFragment<InfoContract.Presenter> implements InfoContract.View{

    public static
    InfoFragment getInstance() {
        return new InfoFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_info_customer;
    }
}
