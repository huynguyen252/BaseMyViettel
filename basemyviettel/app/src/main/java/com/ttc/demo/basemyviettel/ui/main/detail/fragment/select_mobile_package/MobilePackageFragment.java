package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.MVShopResultActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.model.MobilePackage;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ServicePackage;
import com.ttc.demo.basemyviettel.ui.main.detail.response.MobilePackageResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.ServicePackageResponse;
import com.ttc.demo.basemyviettel.ui.main.listener.OnMobilePackageClickListener;
import com.ttc.demo.basemyviettel.ui.main.listener.OnVasPackageClickListener;
import com.ttc.demo.basemyviettel.utils.Constants;
import com.ttc.demo.basemyviettel.utils.DialogUtils;
import com.ttc.demo.basemyviettel.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class MobilePackageFragment extends ViewFragment<MobilePackageContract.Presenter>
        implements MobilePackageContract.View,
        OnMobilePackageClickListener, OnVasPackageClickListener {

    @BindView(R.id.product_package_recyclerview)
    RecyclerView revMobilePackage;
    @BindView(R.id.vas_package_recyclerview)
    RecyclerView revVasPackage;
    @BindView(R.id.product_package_button)
    Button btnContinue;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.seekbar_vtshop)
    SeekBar seekBar;
    @BindView(R.id.ll_money_card)
    View layoutMoneyCard;

    private MobileAdapter mobileAdapter;
    private ServiceAdapter serviceAdapter;
    private
    List<MobilePackage> listMobile;
    private List<ServicePackage> listVas;
    private MobilePackageContract.Presenter presenter;
    public static final int VALUE_MONEY = 10000;
    private MobilePackage mobileSelected;
    private ServicePackage vasSelected;

    public static
    MobilePackageFragment getInstance() {
        return new MobilePackageFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_select_mobile_package;
    }

    @Override
    public
    void initLayout() {
        super.initLayout();
        ButterKnife.bind(getActivity());
        init();
        checkTypeNumberSelected();
        listener();
        presenter = new MobilePackagePresenter(getActivity(), this);
        presenter.getDataPackage();
    }

    private void checkTypeNumberSelected(){
        Constants.ORDER_TYPE typeCheck = (Constants.ORDER_TYPE) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_NUMBER_TYPE);
        if(typeCheck == Constants.ORDER_TYPE.PRE || typeCheck == null){
            layoutMoneyCard.setVisibility(View.VISIBLE);
            tvInfo.setVisibility(View.VISIBLE);
            seekBar.setVisibility(View.VISIBLE);
            seekBar.setProgress(0);
            tvInfo.setText(NumberUtils.formatPriceNumber(seekBar.getProgress() * VALUE_MONEY)+"đ");
        }else if (typeCheck == Constants.ORDER_TYPE.POS){
            seekBar.setProgress(0);
            tvInfo.setText(NumberUtils.formatPriceNumber(seekBar.getProgress() * VALUE_MONEY)+"đ");
            layoutMoneyCard.setVisibility(View.GONE);
            tvInfo.setVisibility(View.GONE);
            seekBar.setVisibility(View.GONE);
        }
    }

    private void init(){
        btnContinue.setEnabled(false);
        btnContinue.setBackgroundResource(R.drawable.bg_button_disable);
        revMobilePackage.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        revMobilePackage.setHasFixedSize(true);
        revVasPackage.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        revVasPackage.setHasFixedSize(true);
        listMobile = new ArrayList<>();
        listVas = new ArrayList<>();
        mobileAdapter = new MobileAdapter(getActivity(), listMobile, this, mobileSelected);
        serviceAdapter = new ServiceAdapter(getActivity(), listVas, this, vasSelected);
        revMobilePackage.setAdapter(mobileAdapter);
        revVasPackage.setAdapter(serviceAdapter);
    }

    private void listener(){
        btnContinue.setOnClickListener(v -> {
            mPresenter.onStepMobilePackageDone();
            //save data
            MVShopResultActivity.orderState.put(MVShopResultActivity.STEP_PACKAGE_MOBILE, mobileSelected);
            MVShopResultActivity.orderState.put(MVShopResultActivity.STEP_PACKAGE_VAS, vasSelected);
            MVShopResultActivity.orderState.put(MVShopResultActivity.STEP_PACKAGE_MONEY_CARD, String.valueOf(seekBar.getProgress()));
        });
        tvInfo.setText(NumberUtils.formatPriceNumber(seekBar.getProgress() * VALUE_MONEY)+"đ");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public
            void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //setProgress(progress);
                tvInfo.setText(NumberUtils.formatPriceNumber(progress * VALUE_MONEY)+"đ");
            }
            @Override
            public
            void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public
            void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public
    void setDataPackage(List<Object> list) {
        DialogUtils.dismissProgressDialog();
        listMobile.clear();
        listVas.clear();
        if(!list.isEmpty()){
            for (Object o : list){
                if (o instanceof MobilePackageResponse){
                    if(((MobilePackageResponse) o).getData() != null){
                        for (MobilePackage m : ((MobilePackageResponse) o).getData()){
                            listMobile.add(m);
                        }
                    }
                }
                else if(o instanceof ServicePackageResponse){
                    if(((ServicePackageResponse) o).getData()!= null){
                        for (ServicePackage s : ((ServicePackageResponse) o).getData()){
                            listVas.add(s);
                        }
                    }
                }
            }
        }
        mobileAdapter.notifyDataSetChanged();
        serviceAdapter.notifyDataSetChanged();
    }

    @Override
    public
    void onMobileSelectedClick(MobilePackage mobilePackage) {
        if(mobilePackage != null){
            mobileSelected = mobilePackage;
            btnContinue.setEnabled(true);
            btnContinue.setBackgroundResource(R.drawable.bg_button);
            btnContinue.setTextColor(Color.WHITE);
        }
    }

    @Override
    public
    void onMobileDetailClick(MobilePackage mobilePackage) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_package_detail);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        TextView tvPrice = dialog.findViewById(R.id.tv_price);
        TextView tvCycle = dialog.findViewById(R.id.tv_time);
        TextView tvDescription = dialog.findViewById(R.id.tv_descripstion);
        tvTitle.setText(mobilePackage.getShort_name());
        tvPrice.setText(NumberUtils.formatPriceNumber(mobilePackage.getPrice())+"đ");
        tvCycle.setText(mobilePackage.getBundle_cycle());
        tvDescription.setText(mobilePackage.getService_message());
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        dialog.show();
    }

    @Override
    public
    void onVasItemClick(ServicePackage servicePackage) {
        if(servicePackage != null){
            vasSelected = servicePackage;
        }
    }

    @Override
    public
    void onVasDetailClick(ServicePackage servicePackage) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_package_detail);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        TextView tvPrice = dialog.findViewById(R.id.tv_price);
        TextView tvCycle = dialog.findViewById(R.id.tv_time);
        TextView tvDescription = dialog.findViewById(R.id.tv_descripstion);
        tvTitle.setText(servicePackage.getShort_name());
        tvPrice.setText(NumberUtils.formatPriceNumber(servicePackage.getPrice())+"đ");
        tvCycle.setText("--");
        tvDescription.setText(servicePackage.getService_message());
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        dialog.show();
    }
}
