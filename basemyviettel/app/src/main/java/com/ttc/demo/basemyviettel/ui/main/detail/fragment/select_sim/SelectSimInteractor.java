package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import com.gemvietnam.base.viper.Interactor;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;

public
class SelectSimInteractor extends Interactor<SelectSimContract.Presenter> implements SelectSimContract.Interactor{

    public
    SelectSimInteractor(SelectSimContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public
    void getCommonSetting(String token, ViettelCallback<GetCommonSettingResult> callback) {

    }
}
