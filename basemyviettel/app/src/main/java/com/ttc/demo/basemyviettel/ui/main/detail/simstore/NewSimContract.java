package com.ttc.demo.basemyviettel.ui.main.detail.simstore;

import androidx.fragment.app.Fragment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;

public
interface NewSimContract {
    interface Interactor extends IInteractor<Presenter> {
        void getCommonSetting(String token, ViettelCallback<GetCommonSettingResult> callback);
    }

    interface View extends PresentView<Presenter> {
        void setSwitchFragmentStoreSim(Fragment fragment, int position);
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void getSwitchFragmentStoreSim(int position);
        void getInitFragmentStoreSim(int position);
    }
}
