package com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet.BottomDistrictFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet.BottomSheetFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment.PaymentContract;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment.PaymentPresenter;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaDetailModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.DistrictModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ShopAreaModel;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaDetailResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaResponse;
import com.ttc.demo.basemyviettel.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class ShipmentFragment extends ViewFragment<ShipmentContract.Presenter> implements ShipmentContract.View {

    @BindView(R.id.province_layout)
    View layoutProvince;
    @BindView(R.id.province_edt)
    TextView tvProvince;
    @BindView(R.id.district_layout)
    View layoutDistrict;
    @BindView(R.id.district_edt)
    TextView tvDistrict;
    @BindView(R.id.rev_shop_are)
    RecyclerView revShopArea;
    @BindView(R.id.btn_search_shop_area)
    Button btnSearchShop;

    private List<ShopAreaModel> listShopArea;
    private ShopAreaAdapter shopAreaAdapter;
    private
    ShipmentContract.Presenter presenter;

    private List<AreaModel> listProvince;
    private List<AreaDetailModel> listProvinceDetail;
    private List<DistrictModel> listDistrict;
    private DistrictModel keyModel;

    public static ShipmentFragment getInstance(){
        return new ShipmentFragment();
    }

    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_detail_vtshop_shipment;
    }

    @Override
    public
    void initLayout() {
        super.initLayout();
        ButterKnife.bind(getActivity());
        init();
        checkArea();
        listener();
        presenter = new ShipmentPresenter(getActivity(), this);
        presenter.getListArea();
        //presenter.getListShopAreaByCode();
    }

    private void init(){
        listShopArea = new ArrayList<>();
        listProvince = new ArrayList<>();
        listProvinceDetail = new ArrayList<>();
        listDistrict = new ArrayList<>();
        shopAreaAdapter = new ShopAreaAdapter(listShopArea);
        revShopArea.setHasFixedSize(true);
        revShopArea.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        revShopArea.setAdapter(shopAreaAdapter);
    }
    private void checkArea(){

    }
    private
    void listener() {
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
                Log.d("district", "click: "+listDistrict.size());
                BottomDistrictFragment bottomDistrictFragment = new BottomDistrictFragment(listDistrict, districtModel -> {
                    tvDistrict.setText(districtModel.getName());
                    keyModel = districtModel;
                    Log.d("callByCode", "listener: "+keyModel.getId());
                });
                bottomDistrictFragment.show(getChildFragmentManager(), bottomDistrictFragment.getTag());
            });
            bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());

        });
        layoutDistrict.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Bạn phải chọn tỉnh/thành phố trước",Toast.LENGTH_LONG).show();
        });
        btnSearchShop.setOnClickListener(v -> {
            if(keyModel == null){
                return;
            }
            else {
                presenter.getListShopAreaByCode(keyModel.getId());
            }
        });
    }

    @Override
    public
    void setListShopAreaByCode(List<ShopAreaModel> list) {
        DialogUtils.dismissProgressDialog();
        listShopArea.clear();
        for (ShopAreaModel s : list){
            listShopArea.add(s);
        }
        shopAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public
    void setListArea(List<Object> list) {
        DialogUtils.dismissProgressDialog();
        listProvince.clear();
        listProvinceDetail.clear();
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
        Log.d("Province", listProvince.size() +" "+listProvinceDetail.size());
    }
}
