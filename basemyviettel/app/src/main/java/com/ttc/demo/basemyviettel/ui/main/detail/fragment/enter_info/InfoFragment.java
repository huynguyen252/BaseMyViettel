package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.MVShopResultActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet.AreaAdapter;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet.BottomDistrictFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet.BottomSheetFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet.DistrictAdapter;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaDetailModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.DistrictModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.InfoCustomer;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaDetailResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaResponse;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class InfoFragment extends ViewFragment<InfoContract.Presenter> implements InfoContract.View, TextWatcher{

    @BindView(R.id.tv_choose_tem)
    TextView btnChooseTem;
    @BindView(R.id.ll_name)
    View layoutName;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.ll_phone)
    View layoutPhone;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.ll_date_of_birth)
    View layout_dateOfBirth;
    @BindView(R.id.tv_date_of_birth)
    TextView tvDateOfBirth;
    @BindView(R.id.province_layout)
    View layoutProvince;
    @BindView(R.id.province_edt)
    TextView tvProvince;
    @BindView(R.id.district_layout)
    View layoutDistrict;
    @BindView(R.id.district_edt)
    TextView tvDistrict;
    @BindView(R.id.commune_layout)
    View layoutCommune;
    @BindView(R.id.commune_edt)
    TextView tvCommune;
    @BindView(R.id.ll_address)
    View layoutAddress;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.ll_papers_info)
    View layoutPaperInfo;
    @BindView(R.id.tv_papers_info)
    EditText tvPaperInfo;
    @BindView(R.id.tv_ngay_cap)
    TextView tvNgayCap;
    @BindView(R.id.tv_noi_cap)
    TextView tvNoiCap;
    @BindView(R.id.nhap_thong_tin_continue_btn)
    TextView btnContinue;

    private List<AreaModel> listProvince;
    private List<AreaDetailModel> listProvinceDetail;
    private List<DistrictModel> listDistrict;
    private InfoContract.Presenter presenter;
    private boolean checkField = false;

    public static
    InfoFragment getInstance() {
        return new InfoFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_info_customer;
    }

    @Override
    public
    void initLayout() {
        super.initLayout();
        ButterKnife.bind(getActivity());
        init();
        checkInfo();
        presenter = new InfoPresenter(getActivity(), this);
        presenter.getListArea();
        listener();
    }

    private void checkInfo(){
        InfoCustomer info = (InfoCustomer) MVShopResultActivity.orderState.get(MVShopResultActivity.STEP_INFO_CUSTOMER);
        if(info!= null){
            tvName.setText(info.getName());
            tvPhone.setText(info.getPhone());
            tvDateOfBirth.setText(info.getBirthDay());
            tvProvince.setText(info.getCity());
            tvDistrict.setText(info.getDistrict());
            tvCommune.setText(info.getCommune());
            tvAddress.setText(info.getAddressDetail());
            tvPaperInfo.setText(info.getInfoPapers());
            tvNgayCap.setText(info.getNgayCap());
            tvNoiCap.setText(info.getNoiCap());
            checkField = checkField();
            setCheckField(checkField);
        }
    }

    private boolean checkField(){
        boolean check;
        if(tvName.getText().toString().isEmpty() || tvPhone.getText().toString().isEmpty() || tvDateOfBirth.getText().toString().isEmpty()
                || tvProvince.getText().toString().isEmpty() || tvDistrict.getText().toString().isEmpty()
            || tvAddress.getText().toString().isEmpty() || tvPaperInfo.getText().toString().isEmpty()
            || tvNgayCap.getText().toString().isEmpty() || tvNoiCap.getText().toString().isEmpty()){
            check = false;
        }else {
            check = true;
        }
        return check;
    }

    private void setCheckField(boolean check){
        if(check == true){
            btnContinue.setEnabled(true);
            btnContinue.setBackgroundResource(R.drawable.bg_button);
        }
        else {
            btnContinue.setEnabled(false);
            btnContinue.setBackgroundResource(R.drawable.bg_button_disable);
        }
    }

    private void init(){
        listProvince = new ArrayList<>();
        listProvinceDetail = new ArrayList<>();
        listDistrict = new ArrayList<>();
        layoutCommune.setVisibility(View.GONE);
        setCheckField(checkField);
    }

    private void listener(){
        layoutProvince.setOnClickListener(v -> {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(listProvince, areaModel -> {
                tvProvince.setText(areaModel.getName());
                listDistrict.clear();
                for (AreaDetailModel areaDetailModel : listProvinceDetail){
                    if (areaDetailModel.getName().equals(tvProvince.getText())){
                        for (DistrictModel d : areaDetailModel.getParentCity()){
                            listDistrict.add(d);
                        }
                    }
                }
                BottomDistrictFragment bottomDistrictFragment = new BottomDistrictFragment(listDistrict, districtModel -> {
                    tvDistrict.setText(districtModel.getName());
                });
                bottomDistrictFragment.show(getChildFragmentManager(), bottomDistrictFragment.getTag());
            });
            bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());

        });
        layoutDistrict.setOnClickListener(v -> {
            Toast.makeText(getActivity(), R.string.message_district,Toast.LENGTH_LONG).show();
        });
        btnContinue.setOnClickListener(v -> {
            mPresenter.onStepEnterInfoDone();
            InfoCustomer info = new InfoCustomer(tvName.getText().toString(),tvPhone.getText().toString(),
                    tvDateOfBirth.getText().toString(), tvProvince.getText().toString(),
                    tvDistrict.getText().toString(), "", tvAddress.getText().toString(),
                    tvPaperInfo.getText().toString(), tvNgayCap.getText().toString(), tvNoiCap.getText().toString());
            //save data
            MVShopResultActivity.orderState.put(MVShopResultActivity.STEP_INFO_CUSTOMER, info);
        });
        tvName.addTextChangedListener((TextWatcher) this);
        tvPhone.addTextChangedListener((TextWatcher) this);
        tvDateOfBirth.addTextChangedListener((TextWatcher) this);
        tvAddress.addTextChangedListener((TextWatcher) this);
        tvPaperInfo.addTextChangedListener((TextWatcher) this);
        tvNgayCap.addTextChangedListener((TextWatcher) this);
        tvNoiCap.addTextChangedListener((TextWatcher) this);
    }

    @Override
    public
    void setListArea(List<Object> list) {
        DialogUtils.dismissProgressDialog();
        listProvince.clear();
        listProvinceDetail.clear();
        if(!list.isEmpty()){
            for (Object o : list){
                if(o instanceof AreaResponse){
                    if(((AreaResponse) o).getData() != null){
                        for (AreaModel a : ((AreaResponse) o).getData()){
                            listProvince.add(a);
                        }
                    }
                }
                if (o instanceof AreaDetailResponse){
                    if(((AreaDetailResponse) o).getData()!= null){
                        for (AreaDetailModel a : ((AreaDetailResponse) o).getData()){
                            listProvinceDetail.add(a);
                        }
                    }
                }
            }
        }
    }

    @Override
    public
    void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public
    void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public
    void afterTextChanged(Editable s) {
        if (checkField() == true){
            checkField = true;
            setCheckField(checkField);
        }
    }
}
