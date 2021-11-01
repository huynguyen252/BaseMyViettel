package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.IView;
import com.gemvietnam.base.viper.interfaces.PresentView;

public
interface InfoContract {
    interface Interactor extends IInteractor<Presenter> {}
    interface View extends PresentView<Presenter> {}
    interface Presenter extends IPresenter<View, Interactor> {}
}
