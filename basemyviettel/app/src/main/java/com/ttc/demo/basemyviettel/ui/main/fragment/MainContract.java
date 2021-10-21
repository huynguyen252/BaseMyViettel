package com.ttc.demo.basemyviettel.ui.main.fragment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;

/**
 * The ChangeAccount Contract
 */
interface MainContract {

    interface Interactor extends IInteractor<Presenter> {
        void getCommonSetting(String token, ViettelCallback<GetCommonSettingResult> callback);
    }

    interface View extends PresentView<Presenter> {
        void setInformation(GetCommonSettingResult getCommonSettingResult);
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void getCommonSetting(String token);
    }
}



