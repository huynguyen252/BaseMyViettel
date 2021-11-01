package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import androidx.fragment.app.Fragment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;

public
interface SelectSimContract {
    interface Interactor extends IInteractor<Presenter> {
        void getCommonSetting(String token, ViettelCallback<GetCommonSettingResult> callback);
    }

    interface View extends PresentView<Presenter> {

    }

    interface Presenter extends IPresenter<View, Interactor> {

    }
}
