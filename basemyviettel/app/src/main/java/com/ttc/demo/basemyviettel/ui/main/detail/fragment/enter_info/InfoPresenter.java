package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

public
class InfoPresenter extends Presenter<InfoContract.View, InfoContract.Interactor> implements InfoContract.Presenter{
    public
    InfoPresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public
    void start() {

    }

    @Override
    public
    InfoContract.Interactor onCreateInteractor() {
        return new InfoInteractor(this);
    }

    @Override
    public
    InfoContract.View onCreateView() {
        return InfoFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {

    }
}
