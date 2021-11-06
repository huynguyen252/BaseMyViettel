package com.ttc.demo.basemyviettel.ui.main.detail.simstore;

import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.InfoPresenter;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment.PaymentPresenter;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package.MobilePackagePresenter;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim.SelectSimPresenter;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment.ShipmentPresenter;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;

public
class NewSimPresenter extends Presenter<NewSimContract.View, NewSimContract.Interactor>
        implements NewSimContract.Presenter, OnStepDoneListener{

    private ContainerView containerView;

    private
    SelectSimPresenter selectSimPresenter;
    private
    MobilePackagePresenter mobilePackagePresenter;
    private
    InfoPresenter infoPresenter;
    private
    ShipmentPresenter shipmentPresenter;
    private
    PaymentPresenter paymentPresenter;
    public NewSimPresenter(ContainerView containerView) {
        super(containerView);
        this.containerView = containerView;
    }

    @Override
    public
    void start() {

    }

    @Override
    public
    NewSimContract.Interactor onCreateInteractor() {
        return new NewSimInteractor(this);
    }

    @Override
    public
    NewSimContract.View onCreateView() {
        return NewSimFragment.getInstance();
    }

    @Override
    public
    void onDestroyView() {

    }

    @Override
    public
    void getSwitchFragmentStoreSim(int position) {
        switch (position) {
            case NewSimFragment.NUMBER_CHON_SO:
                if (selectSimPresenter == null){
                    selectSimPresenter = new SelectSimPresenter(mContainerView);
                }
                mView.setSwitchFragmentStoreSim(selectSimPresenter.setSelectSimListener(this).getFragment(), position);
                break;
            case NewSimFragment.NUMBER_CHON_GOI_CUOC:
                if (mobilePackagePresenter == null){
                    mobilePackagePresenter = new MobilePackagePresenter(mContainerView);
                }
                mView.setSwitchFragmentStoreSim(mobilePackagePresenter.setSelectedMobilePackageListener(this).getFragment(), position);
                break;
            case NewSimFragment.NUMBER_NHAP_THONG_TIN:
                if (infoPresenter == null){
                    infoPresenter = new InfoPresenter(mContainerView);
                }
                mView.setSwitchFragmentStoreSim(infoPresenter.setSelectInfoListener(this).getFragment(), position);
                break;
            case NewSimFragment.NUMBER_GIAO_HANG:
                if (shipmentPresenter == null){
                    shipmentPresenter = new ShipmentPresenter(mContainerView);
                }
                mView.setSwitchFragmentStoreSim(shipmentPresenter.getFragment(), position);
                break;
            case NewSimFragment.NUMBER_THANH_TOAN:
                if (paymentPresenter == null){
                    paymentPresenter = new PaymentPresenter(mContainerView);
                }
                mView.setSwitchFragmentStoreSim(paymentPresenter.getFragment(), position);
                break;
            default:
                break;
        }
    }

    @Override
    public
    void getInitFragmentStoreSim(int position) {
        if(position == NewSimFragment.NUMBER_CHON_SO) {
            if(selectSimPresenter == null){
                selectSimPresenter = new SelectSimPresenter(mContainerView);
            }
            mView.setSwitchFragmentStoreSim(selectSimPresenter.setSelectSimListener(this).getFragment(), position);
        }
        else if(position == NewSimFragment.NUMBER_CHON_GOI_CUOC) {
            if(mobilePackagePresenter == null){
                mobilePackagePresenter = new MobilePackagePresenter(mContainerView);
            }
            mView.setSwitchFragmentStoreSim(mobilePackagePresenter.setSelectedMobilePackageListener(this).getFragment(), position);
        }
        else if(position == NewSimFragment.NUMBER_NHAP_THONG_TIN) {
            if(infoPresenter == null){
                infoPresenter = new InfoPresenter(mContainerView);
            }
            mView.setSwitchFragmentStoreSim(infoPresenter.setSelectInfoListener(this).getFragment(), position);
        }
        else if (position == NewSimFragment.NUMBER_GIAO_HANG) {
            if(shipmentPresenter == null){
                shipmentPresenter = new ShipmentPresenter(mContainerView);
            }
            mView.setSwitchFragmentStoreSim(shipmentPresenter.getFragment(), position);
        }
        else if (position == NewSimFragment.NUMBER_THANH_TOAN){
            if(paymentPresenter == null){
                paymentPresenter = new PaymentPresenter(mContainerView);
            }
            mView.setSwitchFragmentStoreSim(paymentPresenter.getFragment(), position);
        }
    }

    @Override
    public
    void onStepSelectSimDone() {
        if (mobilePackagePresenter == null){
            mobilePackagePresenter = new MobilePackagePresenter(mContainerView);
        }
        mView.setSwitchFragmentStoreSim(mobilePackagePresenter.setSelectedMobilePackageListener(this).getFragment(), NewSimFragment.NUMBER_CHON_GOI_CUOC);
    }

    @Override
    public
    void onStepSelectMobilePackageDone() {
        if(infoPresenter == null){
            infoPresenter = new InfoPresenter(mContainerView);
        }
        mView.setSwitchFragmentStoreSim(infoPresenter.setSelectInfoListener(this).getFragment(), NewSimFragment.NUMBER_NHAP_THONG_TIN);
    }

    @Override
    public
    void onStepEnterInfoDone() {
        if(shipmentPresenter == null){
            shipmentPresenter = new ShipmentPresenter(mContainerView);
        }
        mView.setSwitchFragmentStoreSim(shipmentPresenter.getFragment(), NewSimFragment.NUMBER_GIAO_HANG);
    }
}
