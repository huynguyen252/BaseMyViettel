package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.ui.main.detail.model.MobilePackage;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ServicePackage;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;

import java.util.List;

public
interface MobilePackageContract {
    interface Interactor extends IInteractor<Presenter>{

    }
    interface View extends PresentView<Presenter>{
        void setDataPackage(List<Object> list);
    }
    interface Presenter extends IPresenter<View, Interactor>{
        void getDataPackage();
        MobilePackagePresenter setSelectedMobilePackageListener(OnStepDoneListener listener);
        OnStepDoneListener getListener();
        void onStepMobilePackageDone();
    }
}
