package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim.SelectSimFragment;

public
class MobilePackagePresenter extends Presenter<MobilePackageContract.View, MobilePackageContract.Interactor> implements MobilePackageContract.Presenter{
    public
    MobilePackagePresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public
    void start() {

    }

    @Override
    public
    MobilePackageContract.Interactor onCreateInteractor() {
        return new MobilePackageInteractor(this);
    }

    @Override
    public
    MobilePackageContract.View onCreateView() {
        return MobilePackageFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {

    }
}
