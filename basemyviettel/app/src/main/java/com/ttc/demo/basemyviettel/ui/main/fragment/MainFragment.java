package com.ttc.demo.basemyviettel.ui.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.ui.main.adapter.ListHomeAdapter;
import com.ttc.demo.basemyviettel.ui.main.adapter.SlideAdapter;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVThemeProductModel;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVThemeProductResponse;
import com.ttc.demo.basemyviettel.ui.main.model.sim.MobileModel;
import com.ttc.demo.basemyviettel.ui.main.model.sim.ShopHomeResponse;
import com.ttc.demo.basemyviettel.ui.main.model.sim.SimModel;
import com.ttc.demo.basemyviettel.ui.main.model.sim.TopBannerModel;
import com.ttc.demo.basemyviettel.utils.Constants;
import com.ttc.demo.basemyviettel.utils.DialogUtils;
import com.ttc.demo.basemyviettel.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends ViewFragment<MainContract.Presenter>
        implements MainContract.View {

    @BindView(R.id.tv_result)
    AppCompatTextView tvResult;

    @BindView(R.id.listInfo)
    RecyclerView listInfo;

    private View mContentView;
    private MainContract.Presenter mainShopPresenter;
    private
    ListHomeAdapter listHomeAdapter;
    private
    ArrayList<TopBannerModel> listBanner;
    private ArrayList<SimModel> listSim;
    private ArrayList<MobileModel> listMobile;
    private ArrayList<MVThemeProductModel> listTheme;
    private Timer mTimer;

    @Override
    public
    void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainShopPresenter = new MainPresenter(getActivity(),this);
        listBanner = new ArrayList<>();
        listSim = new ArrayList<>();
        listMobile = new ArrayList<>();
        listTheme = new ArrayList<>();
        //bannerAdapter = new SlideAdapter(getActivity(), listBanner);
        mTimer = new Timer();

    }

    @Nullable
    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mContentView == null){
            mContentView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, mContentView);
            setup();
        }
        return mContentView;
    }

    @Override
    public
    void onDestroyView() {
        super.onDestroyView();
        if (mTimer!=null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void setup(){
        listInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        listInfo.setHasFixedSize(true);
        listHomeAdapter = new ListHomeAdapter(getActivity(), mTimer, listBanner, listSim, listMobile, listTheme);
        listInfo.setAdapter(listHomeAdapter);
        callApiShopHome();

    }

    private void callApiShopHome(){
//        mainShopPresenter.getShopHomeResult();
//        mainShopPresenter.getThemeProductResult(Constants.PRODUCT.LIMIT);
        mainShopPresenter.getAllCategoryShopHome(Constants.PRODUCT.LIMIT);
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mPresenter.getCommonSetting("");
    }

    @Override
    public void setInformation(GetCommonSettingResult getCommonSettingResult) {
        tvResult.setText(getCommonSettingResult.getMessage());
    }

//    @Override
//    public
//    void setShopHome(ShopHomeResponse shopHome) {
//        listBanner.clear();
//        listSim.clear();
//        listMobile.clear();
//        if(shopHome.getData().getTopBanner() != null){
//            for(TopBannerModel item : shopHome.getData().getTopBanner()){
//                listBanner.add(item);
//                Log.d("callShopHome", item.getImage());
//            }
//        }
//        if (shopHome.getData().getSim() != null){
//            for (SimModel item : shopHome.getData().getSim()){
//                listSim.add(item);
//            }
//        }
//        if (shopHome.getData().getMobile() != null){
//            for (MobileModel item : shopHome.getData().getMobile()){
//                listMobile.add(item);
//            }
//        }
//        //bannerAdapter.notifyDataSetChanged();
//        listHomeAdapter.notifyDataSetChanged();
//        //Log.d("callShopHome", shopHome.getData().getSim().get(1).getIsdn() + shopHome.getData().getMobile().get(1).getImage());
//    }

//    @Override
//    public
//    void setThemeProduct(MVThemeProductResponse themeProduct) {
//        listTheme.clear();
//        if(themeProduct != null){
//            for (MVThemeProductModel item : themeProduct.getListThemeProductModel()){
//                listTheme.add(item);
//            }
//        }
//        listHomeAdapter.notifyDataSetChanged();
//    }

    @Override
    public
    void setAllCategoryShopHome(List<Object> list) {
        listBanner.clear();
        listSim.clear();
        listMobile.clear();
        listTheme.clear();
        DialogUtils.dismissProgressDialog();
        for (Object o : list){
            if(o instanceof ShopHomeResponse){
                if(((ShopHomeResponse) o).getData().getTopBanner() != null){
                    for(TopBannerModel item : ((ShopHomeResponse) o).getData().getTopBanner()){
                        listBanner.add(item);
                        Log.d("callShopHome", item.getImage());
                    }
                }
                if(((ShopHomeResponse) o).getData().getSim() != null){
                    for(SimModel item : ((ShopHomeResponse) o).getData().getSim()){
                        listSim.add(item);
                    }
                }
                if(((ShopHomeResponse) o).getData().getMobile() != null){
                    for(MobileModel item : ((ShopHomeResponse) o).getData().getMobile()){
                        listMobile.add(item);
                    }
                }
            }
            if(o instanceof MVThemeProductResponse){
                if(((MVThemeProductResponse) o).getListThemeProductModel() != null){
                    for (MVThemeProductModel item : ((MVThemeProductResponse) o).getListThemeProductModel()){
                        listTheme.add(item);
                    }
                }
            }
        }
        listHomeAdapter.notifyDataSetChanged();
    }

}
