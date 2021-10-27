package com.ttc.demo.basemyviettel.ui.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.data.NetWorkController;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;
import com.ttc.demo.basemyviettel.ui.main.adapter.ListHomeAdapter;
import com.ttc.demo.basemyviettel.ui.main.adapter.SlideAdapter;
import com.ttc.demo.basemyviettel.ui.main.model.MobileModel;
import com.ttc.demo.basemyviettel.ui.main.model.ShopHomeResult;
import com.ttc.demo.basemyviettel.ui.main.model.SimModel;
import com.ttc.demo.basemyviettel.ui.main.model.TopBannerModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

    @BindView(R.id.listInfo)
    RecyclerView listInfo;

    private ContainerView containerView;
    private View mContentView;
    private MainContract.Presenter mainShopPresenter;
    private
    SlideAdapter bannerAdapter;
    private
    ListHomeAdapter listHomeAdapter;
    private
    ArrayList<TopBannerModel> listBanner;
    private ArrayList<SimModel> listSim;
    private ArrayList<MobileModel> listMobile;
    private Timer mTimer;

    @Override
    public
    void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainShopPresenter = new MainPresenter(this);
        listBanner = new ArrayList<>();
        listSim = new ArrayList<>();
        listMobile = new ArrayList<>();
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
//        listHomeAdapter = new ListHomeAdapter(getActivity(), mTimer, listBanner, listSim, listMobile);
//        listInfo.setAdapter(listHomeAdapter);
        callApiShopHome();

    }

    private void callApiShopHome(){
        mainShopPresenter.getShopHomeResult();
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
        listBanner.clear();
        listSim.clear();
        listMobile.clear();
        if(shopHome.getData().getTopBanner() != null){
            for(TopBannerModel item : shopHome.getData().getTopBanner()){
                listBanner.add(item);
                Log.d("callShopHome", item.getImage());
            }
        }
        if (shopHome.getData().getSim() != null){
            for (SimModel item : shopHome.getData().getSim()){
                listSim.add(item);
            }
        }
        if (shopHome.getData().getMobile() != null){
            for (MobileModel item : shopHome.getData().getMobile()){
                listMobile.add(item);
            }
        }
        //bannerAdapter.notifyDataSetChanged();
        listHomeAdapter = new ListHomeAdapter(getActivity(), mTimer, listBanner, listSim, listMobile);
        listInfo.setAdapter(listHomeAdapter);
        listHomeAdapter.notifyDataSetChanged();
        //Log.d("callShopHome", shopHome.getData().getSim().get(1).getIsdn() + shopHome.getData().getMobile().get(1).getImage());
    }

}
