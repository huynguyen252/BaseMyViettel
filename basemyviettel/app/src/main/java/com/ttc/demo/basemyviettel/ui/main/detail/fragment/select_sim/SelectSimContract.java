package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import androidx.fragment.app.Fragment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;

import java.util.List;

public
interface SelectSimContract {
    interface Interactor extends IInteractor<Presenter> {
        void getCommonSetting(String token, ViettelCallback<GetCommonSettingResult> callback);
    }

    interface View extends PresentView<Presenter> {
        void setListNumber(List<NumberModel> listNumber);
        void saveBlockSimInfoSuccess();
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void getListNumber();
        void saveBlockSimInfo();
        SelectSimPresenter setSelectSimListener(OnStepDoneListener listener);
        OnStepDoneListener getListener();
        void onStepSelectMobilePackage();
    }
}
