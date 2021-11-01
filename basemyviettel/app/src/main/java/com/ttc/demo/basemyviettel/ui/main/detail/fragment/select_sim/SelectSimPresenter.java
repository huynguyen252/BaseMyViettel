package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

public
class SelectSimPresenter extends Presenter<SelectSimContract.View, SelectSimContract.Interactor> implements SelectSimContract.Presenter {

    public
    SelectSimPresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public
    void start() {

    }

    @Override
    public
    SelectSimContract.Interactor onCreateInteractor() {
        return new SelectSimInteractor(this);
    }

    @Override
    public
    SelectSimContract.View onCreateView() {
        return SelectSimFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {

    }
}
