package com.ttc.demo.basemyviettel.ui.main.adapter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.model.MobileModel;
import com.ttc.demo.basemyviettel.ui.main.model.SimModel;
import com.ttc.demo.basemyviettel.ui.main.model.TopBannerModel;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public
class ListHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TOP_BANNER = 0;
    public static final int SIM_STORE = 1;
    public static final int MOBILE_PACKAGE = 2;

    private
    Timer timer;
    private
    ArrayList<TopBannerModel> listTopBanner;
    private ArrayList<SimModel> listSim;
    private ArrayList<MobileModel> listMobile;

    private Activity context;

    public
    ListHomeAdapter(Activity context, Timer timer,
                    ArrayList<TopBannerModel> listTopBanner,
                    ArrayList<SimModel> listSim,
                    ArrayList<MobileModel> listMobile) {
        this.context = context;
        this.timer = timer;
        this.listTopBanner = listTopBanner;
        this.listSim = listSim;
        this.listMobile = listMobile;
    }

    @NonNull
    @Override
    public
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TOP_BANNER:
                return new BannerHolder(LayoutInflater.from(context)
                        .inflate(R.layout.layout_top_banner_vtshop, parent, false));
            case SIM_STORE:
                return new StoreSimHolder(LayoutInflater.from(context)
                        .inflate(R.layout.layout_store_sim_item, parent, false));
            case MOBILE_PACKAGE:
                return new MobilePackageHolder(LayoutInflater.from(context)
                        .inflate(R.layout.layout_mobile_package_vtshop, parent, false));
            default:
                return null;
        }
    }

    @Override
    public
    void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case TOP_BANNER:
                BannerHolder topBannerHolder = (BannerHolder) holder;
                if(!listTopBanner.isEmpty()){
                    topBannerHolder.vpBanner.setVisibility(View.VISIBLE);
                    SlideAdapter slideAdapter = new SlideAdapter(context, listTopBanner);
                    topBannerHolder.vpBanner.setAdapter(slideAdapter);
                    topBannerHolder.indicator.setViewPager(topBannerHolder.vpBanner);
                    slideAdapter.registerDataSetObserver(topBannerHolder.indicator.getDataSetObserver());
                    //autoscroll
                    timer.schedule(new TimerTask() {
                        @Override
                        public
                        void run() {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                int currentIt =  topBannerHolder.vpBanner.getCurrentItem();
                                int totalIt = listTopBanner.size() - 1;
                                if (currentIt < totalIt){
                                    currentIt++;
                                    topBannerHolder.vpBanner.setCurrentItem(currentIt);
                                }
                                else {
                                    topBannerHolder.vpBanner.setCurrentItem(0);
                                }
                            });
                        }
                    }, 500, 3000);
                }
                break;
            case SIM_STORE:
                StoreSimHolder storeSimHolder = (StoreSimHolder) holder;
                if(!listSim.isEmpty()){
                    storeSimHolder.revSim.setVisibility(View.VISIBLE);
                    storeSimHolder.searchSim.setVisibility(View.VISIBLE);
                    storeSimHolder.posCheckBox.setVisibility(View.VISIBLE);
                    storeSimHolder.preCheckBox.setVisibility(View.VISIBLE);
                    storeSimHolder.button.setVisibility(View.VISIBLE);
                    //storeSimHolder.revSim.setLayoutManager(new LinearLayoutManager(context));
                    StoreSimAdapter simAdapter = new StoreSimAdapter(context, listSim);
                    storeSimHolder.revSim.setAdapter(simAdapter);
                }
                Log.d("listSim", listSim.size()+"");
                break;
            case MOBILE_PACKAGE:
                break;
        }
    }

    @Override
    public
    int getItemCount() {
        return (null == listTopBanner && null == listSim && null == listMobile) ? 1:3;
    }

    @Override
    public
    int getItemViewType(int position) {
        switch (position){
            case 0:
                return TOP_BANNER;
            case 1:
                return SIM_STORE;
            case 2:
                return MOBILE_PACKAGE;
            default:
                return TOP_BANNER;
        }
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.topBanner)
        ViewPager vpBanner;
        @BindView(R.id.indicator)
        CircleIndicator indicator;

        public
        BannerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            int margin = context.getResources().getDimensionPixelSize(R.dimen.size_5dp) * 8;
            vpBanner.setPageMargin(margin / 2);
            vpBanner.setPadding(margin, 0, margin, 0);
            vpBanner.setClipToPadding(false);
            vpBanner.setOffscreenPageLimit(3);
        }
    }

    class StoreSimHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_sim)
        AppCompatEditText searchSim;
        @BindView(R.id.select_number_pre_checkbox)
        AppCompatImageView preCheckBox;
        @BindView(R.id.select_number_pos_checkbox)
        AppCompatImageView posCheckBox;
        @BindView(R.id.select_number_button)
        AppCompatButton button;
        @BindView(R.id.recyclerViewSim)
        RecyclerView revSim;
        public
        StoreSimHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            revSim.setLayoutManager(new GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false));
        }
    }

    class MobilePackageHolder extends RecyclerView.ViewHolder {
        public
        MobilePackageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
