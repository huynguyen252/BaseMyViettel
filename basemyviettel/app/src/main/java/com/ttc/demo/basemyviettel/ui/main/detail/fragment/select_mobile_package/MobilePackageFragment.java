package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;

public
class MobilePackageFragment extends ViewFragment<MobilePackageContract.Presenter> implements MobilePackageContract.View {

    public static
    MobilePackageFragment getInstance() {
        return new MobilePackageFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_select_mobile_package;
    }
}
