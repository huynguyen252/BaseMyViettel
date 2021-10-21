package com.ttc.demo.basemyviettel.ui.main.fragment;

import com.gemvietnam.base.viper.Interactor;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;

import io.reactivex.Observer;

/**
 * The ChangeAccount interactor
 */
class MainInteractor extends Interactor<MainContract.Presenter>
        implements MainContract.Interactor {

    MainInteractor(MainContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void getCommonSetting(String token, Observer<GetCommonSettingResult> observer) {
        NetWorkController.getCommonSettingResult(token,observer);
    }
}
