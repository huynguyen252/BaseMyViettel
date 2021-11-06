package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.MVShopResultActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package.MobilePackageFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package.MobilePackagePresenter;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;
import com.ttc.demo.basemyviettel.ui.main.detail.simstore.NewSimContract;
import com.ttc.demo.basemyviettel.ui.main.detail.simstore.NewSimFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.simstore.NewSimPresenter;
import com.ttc.demo.basemyviettel.ui.main.listener.OnBuySimClickListener;
import com.ttc.demo.basemyviettel.ui.main.listener.OnItemCountChangeListener;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;
import com.ttc.demo.basemyviettel.utils.Constants;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class SelectSimFragment extends ViewFragment<SelectSimContract.Presenter>
        implements SelectSimContract.View, OnBuySimClickListener{

    @BindView(R.id.layout_search)
    LinearLayout mLayoutSearch;
    @BindView(R.id.cancel_layout_search)
    ImageView mCancelLayout;
    @BindView(R.id.search_sim)
    EditText edtSearchSim;
    @BindView(R.id.layout_tra_truoc_option)
    LinearLayout layout_pre;
    @BindView(R.id.layout_tra_sau_option)
    LinearLayout layout_pos;
    @BindView(R.id.select_number_pre_checkbox)
    ImageView checkbox_pre;
    @BindView(R.id.select_number_pos_checkbox)
    ImageView checkbox_pos;
    @BindView(R.id.select_number_button)
    Button btnSearchSim;
    @BindView(R.id.recyclerViewSim)
    RecyclerView recyclerViewSim;
    @BindView(R.id.select_sim_button)
    Button btnContinue;

    private List<NumberModel> list;
    private
    Constants.ORDER_TYPE orderType;
    private SimAdapter adapter;
    private SelectSimContract.Presenter presenter;
    private NumberModel numberModelSelected;

    public static
    SelectSimFragment getInstance() {
        return new SelectSimFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_select_sim;
    }

    @Override
    public
    void initLayout() {
        super.initLayout();
        ButterKnife.bind(getActivity());
        init();
        checkTypeWhenRestartLayout();
        setChecked(orderType);
        listener();
        presenter = new SelectSimPresenter(getActivity(), this);
        presenter.getListNumber();
    }

    private void checkTypeWhenRestartLayout(){
        Constants.ORDER_TYPE typeCheck = (Constants.ORDER_TYPE) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_NUMBER_TYPE);
        if(typeCheck == Constants.ORDER_TYPE.PRE || typeCheck == null){
            orderType = Constants.ORDER_TYPE.PRE;
        }else if(typeCheck == Constants.ORDER_TYPE.POS){
            orderType = Constants.ORDER_TYPE.POS;
        }
    }

    private void init(){
        list = new ArrayList<>();
        checkbox_pre.setImageResource(R.drawable.ic_radio_button_active);
        btnContinue.setEnabled(false);
        btnContinue.setBackgroundResource(R.drawable.bg_button_disable);
        adapter = new SimAdapter(getActivity(),list, orderType,this, numberModelSelected);
        recyclerViewSim.setHasFixedSize(true);
        recyclerViewSim.setItemAnimator(null);
        recyclerViewSim.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerViewSim.setAdapter(adapter);
    }

    private void listener(){
        layout_pre.setOnClickListener(v -> {
            if(orderType != Constants.ORDER_TYPE.PRE){
                setChecked(Constants.ORDER_TYPE.PRE);
                adapter.orderType = orderType;
                adapter.notifyDataSetChanged();
            }
        });
        layout_pos.setOnClickListener(v -> {
            if(orderType != Constants.ORDER_TYPE.POS){
                setChecked(Constants.ORDER_TYPE.POS);
                adapter.orderType = orderType;
                adapter.notifyDataSetChanged();
            }
        });
        edtSearchSim.addTextChangedListener(new TextWatcher() {
            @Override
            public
            void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mLayoutSearch.setBackgroundResource(R.drawable.bg_edittext_circle_seclected);
                btnSearchSim.setBackgroundResource(R.drawable.bg_button);
            }
            @Override
            public
            void onTextChanged(CharSequence s, int start, int before, int count) {
                mLayoutSearch.setBackgroundResource(R.drawable.bg_edittext_circle_seclected);
                mCancelLayout.setVisibility(View.VISIBLE);
            }
            @Override
            public
            void afterTextChanged(Editable s) {
                mCancelLayout.setVisibility(View.VISIBLE);
            }
        });
        mCancelLayout.setOnClickListener(v -> {
            mLayoutSearch.setBackgroundResource(R.drawable.bg_edittext_circle);
            edtSearchSim.setText("");
            edtSearchSim.clearFocus();
            btnSearchSim.setBackgroundResource(R.drawable.bg_button_disable);
            mCancelLayout.setVisibility(View.GONE);
            btnSearchSim.setEnabled(false);
        });
        btnSearchSim.setOnClickListener(v -> {
            //call search sim
        });
        btnContinue.setOnClickListener(v -> {
            //post saveBlockSimInfo
            mPresenter.saveBlockSimInfo();
            mPresenter.onStepSelectMobilePackage();

            //save data number
            MVShopResultActivity.orderState.put(MVShopResultActivity.STEP_NUMBER, numberModelSelected);
            MVShopResultActivity.orderState.put(MVShopResultActivity.STEP_NUMBER_TYPE, orderType);
        });
    }

    @Override
    public
    void setListNumber(List<NumberModel> listNumber) {
        DialogUtils.dismissProgressDialog();
        list.clear();
        if(!listNumber.isEmpty()){
            for (NumberModel i : listNumber){
                list.add(i);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public
    void saveBlockSimInfoSuccess() {

    }

    private void setChecked(Constants.ORDER_TYPE orderT) {
        orderType = orderT;
        if (orderType == Constants.ORDER_TYPE.PRE) {
            checkbox_pre.setImageResource(R.drawable.ic_radio_button_active);
            checkbox_pos.setImageResource(R.drawable.ic_radio_button_default);
        } else {
            checkbox_pos.setImageResource(R.drawable.ic_radio_button_active);
            checkbox_pre.setImageResource(R.drawable.ic_radio_button_default);
        }
        btnSearchSim.clearFocus();
    }

    @Override
    public
    void onBuyClickedListener(NumberModel number) {
        if(number!= null){
            numberModelSelected = number;
            btnContinue.setEnabled(true);
            btnContinue.setBackgroundResource(R.drawable.bg_button);
        }
    }
}
