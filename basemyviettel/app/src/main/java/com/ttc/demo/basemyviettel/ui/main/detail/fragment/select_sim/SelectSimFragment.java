package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;

public
class SelectSimFragment extends ViewFragment<SelectSimContract.Presenter> implements SelectSimContract.View {
    public static
    SelectSimFragment getInstance() {
        return new SelectSimFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_select_sim;
    }
}
