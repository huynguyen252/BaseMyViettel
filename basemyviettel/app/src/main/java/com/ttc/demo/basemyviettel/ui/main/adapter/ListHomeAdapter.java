package com.ttc.demo.basemyviettel.ui.main.adapter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVInfoModel;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVProductGroupModel;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVThemeProductModel;
import com.ttc.demo.basemyviettel.ui.main.model.sim.MobileModel;
import com.ttc.demo.basemyviettel.ui.main.model.sim.SimModel;
import com.ttc.demo.basemyviettel.ui.main.model.sim.TopBannerModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public
class ListHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TOP_BANNER = 0;
    public static final int BOOK = 1;
    public static final int CANDY = 2;
    public static final int SIM_STORE = 3;
    public static final int MOBILE_PACKAGE = 4;

    private
    Timer timer;
    private
    ArrayList<TopBannerModel> listTopBanner;
    private ArrayList<SimModel> listSim;
    private ArrayList<MobileModel> listMobile;
    private ArrayList<MVThemeProductModel> listTheme;

    private Activity context;
    private ImageView[] dots;
    private int viewCountReal = 0;
    private int countDot;
    private int iPositionViewPager;

    public
    ListHomeAdapter(Activity context, Timer timer,
                    ArrayList<TopBannerModel> listTopBanner,
                    ArrayList<SimModel> listSim,
                    ArrayList<MobileModel> listMobile,
                    ArrayList<MVThemeProductModel> listTheme) {
        this.context = context;
        this.timer = timer;
        this.listTopBanner = listTopBanner;
        this.listSim = listSim;
        this.listMobile = listMobile;
        this.listTheme = listTheme;
    }

    @NonNull
    @Override
    public
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TOP_BANNER:
                return new BannerHolder(LayoutInflater.from(context)
                        .inflate(R.layout.layout_top_banner_vtshop, parent, false));
            case BOOK:
                return new CommonHolder(LayoutInflater.from(context)
                        .inflate(R.layout.layout_book_vtshop, parent, false));
            case CANDY:
                return new CommonHolder(LayoutInflater.from(context)
                        .inflate(R.layout.layout_candy_vtshop, parent, false));
            case SIM_STORE:
                return new StoreSimHolder(LayoutInflater.from(context)
                        .inflate(R.layout.layout_store_sim_item, parent, false));
            case MOBILE_PACKAGE:
                return new CommonHolder(LayoutInflater.from(context)
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
                    viewCountReal = slideAdapter.getRealCount();
                    if(slideAdapter.getRealCount() != 0){
                        int numLoop = (slideAdapter.getCount()) / (slideAdapter.getRealCount());
                        topBannerHolder.vpBanner.setCurrentItem(slideAdapter.getRealCount() * (numLoop / 2 + numLoop % 2), false);
                        iPositionViewPager = topBannerHolder.vpBanner.getCurrentItem();
                        drawIndicator(topBannerHolder,0);
                    }
                    iPositionViewPager = topBannerHolder.vpBanner.getCurrentItem();
                    topBannerHolder.vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public
                        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
                        @Override
                        public
                        void onPageSelected(int position) {
                            if (position - 1 == iPositionViewPager) {
                                countDot++;
                                if (countDot < viewCountReal) {
                                    drawIndicator(topBannerHolder,countDot);
                                } else {
                                    countDot = 0;
                                    drawIndicator(topBannerHolder,countDot);
                                }
                            }
                            if (position + 1 == iPositionViewPager) {
                                countDot--;
                                if (countDot < 0) {
                                    countDot = slideAdapter.getRealCount()-1;
                                    drawIndicator(topBannerHolder,countDot);
                                } else {
                                    drawIndicator(topBannerHolder,countDot);
                                }
                            }
                            iPositionViewPager = position;
                        }
                        @Override
                        public
                        void onPageScrollStateChanged(int state) {}
                    });
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
            case BOOK:
                CommonHolder bookHolder = (CommonHolder) holder;
                if(!listTheme.isEmpty()){
                    ArrayList<MVProductGroupModel> list = new ArrayList<>();
                    for (MVThemeProductModel theme : listTheme){
                        if(theme.getThemeName().equals("Sách hay")){
                            list.addAll(theme.getListProductGroup());
                        }
                    }
                    ArrayList<MVInfoModel> listInfo = new ArrayList<>();
                    for (MVProductGroupModel item : list){
                        listInfo.addAll(item.getInfo());
                    }
                    MVProductAdapter bookAdapter = new MVProductAdapter(context, listInfo, 3, 10);
                    bookHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false));
                    bookHolder.recyclerView.setAdapter(bookAdapter);
                }
                break;
            case CANDY:
                CommonHolder candyHolder = (CommonHolder) holder;
                if(!listTheme.isEmpty()){
                    ArrayList<MVProductGroupModel> list = new ArrayList<>();
                    for (MVThemeProductModel theme : listTheme){
                        if(theme.getThemeName().equals("Bánh kẹo")){
                            list.addAll(theme.getListProductGroup());
                        }
                    }
                    ArrayList<MVInfoModel> listInfo = new ArrayList<>();
                    for (MVProductGroupModel item : list){
                        listInfo.addAll(item.getInfo());
                    }
                    MVProductAdapter bookAdapter = new MVProductAdapter(context, listInfo, 3, 10);
                    candyHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false));
                    candyHolder.recyclerView.setAdapter(bookAdapter);
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
                CommonHolder mobileHolder = (CommonHolder) holder;
                if (!listMobile.isEmpty()){
                    mobileHolder.title_tv.setVisibility(View.VISIBLE);
                    mobileHolder.layoutAll.setVisibility(View.VISIBLE);
                    mobileHolder.recyclerView.setVisibility(View.VISIBLE);
                    mobileHolder.title_tv.setText(R.string.goi_cuoc);
                    mobileHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                    MobilePackageAdapter mobileAdapter = new MobilePackageAdapter(listMobile, context);
                    mobileHolder.recyclerView.setAdapter(mobileAdapter);
                }
                else {
                    mobileHolder.title_tv.setVisibility(View.GONE);
                    mobileHolder.layoutAll.setVisibility(View.GONE);
                    mobileHolder.recyclerView.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public
    int getItemCount() {
        return (null == listTopBanner && null == listSim && null == listMobile) ? 1:5;
    }

    @Override
    public
    int getItemViewType(int position) {
        switch (position){
            case 0:
                return TOP_BANNER;
            case 1:
                return BOOK;
            case 2:
                return CANDY;
            case 3:
                return SIM_STORE;
            case 4:
                return MOBILE_PACKAGE;
            default:
                return TOP_BANNER;
        }
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.topBanner)
        ViewPager vpBanner;
        @BindView(R.id.indicator)
        LinearLayout indicator;

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

    class CommonHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.layout_all)
        View layoutAll;
        @BindView(R.id.listItems)
        RecyclerView recyclerView;
        public
        CommonHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void drawIndicator(BannerHolder bannerHolder, int position){
        try {
            if (bannerHolder.indicator != null) {
                bannerHolder.indicator.removeAllViews();
            }
            dots = new ImageView[viewCountReal];
            for (int i = 0; i<dots.length; i++) {
                dots[i] = new ImageView(context);
                if (i == position)
                    dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.dot_indicator_selected));
                else
                    dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.dot_indicator_unselected));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(3, 0, 3, 0);
                bannerHolder.indicator.addView(dots[i], params);
            }
        }catch (Exception e){}
    }

}
