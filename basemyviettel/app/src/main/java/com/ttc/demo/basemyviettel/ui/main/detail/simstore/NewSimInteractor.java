package com.ttc.demo.basemyviettel.ui.main.detail.simstore;

import com.gemvietnam.base.viper.Interactor;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;

public
class NewSimInteractor extends Interactor<NewSimContract.Presenter>
        implements NewSimContract.Interactor{
    public
    NewSimInteractor(NewSimContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public
    void getCommonSetting(String token, ViettelCallback<GetCommonSettingResult> callback) {

    }
}
