package com.ttc.demo.basemyviettel.ui.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.ViewPager;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;
import com.ttc.demo.basemyviettel.ui.main.adapter.SlideAdapter;
import com.ttc.demo.basemyviettel.ui.main.model.ShopHomeResult;
import com.ttc.demo.basemyviettel.ui.main.model.TopBannerModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Response;

public class MainFragment extends ViewFragment<MainContract.Presenter>
        implements MainContract.View {

    @BindView(R.id.tv_result)
    AppCompatTextView tvResult;

    @BindView(R.id.fragment_viettel_shop)
    View fragVTShop;

    @BindView(R.id.topBanner)
    ViewPager vpTopBanner;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    private ContainerView containerView;
    private MainContract.Presenter mainShopPresenter;
    private
    SlideAdapter bannerAdapter;
    private
    ArrayList<TopBannerModel> listBanner;

    @Override
    public
    void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainShopPresenter = new MainPresenter(this);
        listBanner = new ArrayList<>();
        bannerAdapter = new SlideAdapter(getActivity(), listBanner);
    }

    @Nullable
    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        callApiShopHome();
        setup();
        return view;
    }

    private void setup(){
        vpTopBanner.setAdapter(bannerAdapter);
        indicator.setViewPager(vpTopBanner);
        bannerAdapter.registerDataSetObserver(indicator.getDataSetObserver());
    }

    private void callApiShopHome(){
        new Thread(new Runnable() {
            @Override
            public
            void run() {
                mainShopPresenter.getShopHomeResult();
            }
        }).run();
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

    @Override
    public
    void setShopHome(ShopHomeResult shopHome) {
        Toast.makeText(getActivity(),shopHome.getMessage(), Toast.LENGTH_LONG).show();
        for(TopBannerModel item : shopHome.getData().getTopBanner()){
            listBanner.add(item);
            Log.d("callShopHome", item.getImage());
        }
        bannerAdapter.notifyDataSetChanged();
    }
}
