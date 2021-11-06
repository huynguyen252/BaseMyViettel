package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.IView;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;

import java.util.List;

public
interface InfoContract {
    interface Interactor extends IInteractor<Presenter> {}
    interface View extends PresentView<Presenter> {
        void setListArea(List<Object> list);
    }
    interface Presenter extends IPresenter<View, Interactor> {
        void getListArea();
        InfoPresenter setSelectInfoListener(OnStepDoneListener listener);
        OnStepDoneListener getListener();
        void onStepEnterInfoDone();
    }
}
