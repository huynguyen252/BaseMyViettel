package com.ttc.demo.basemyviettel.ui.main.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.model.TopBannerModel;
import com.ttc.demo.basemyviettel.utils.Utilities;

import java.util.ArrayList;

public
class SlideAdapter extends PagerAdapter {
    private Activity context;
    private
    ArrayList<TopBannerModel> listTopBanner;

    public
    SlideAdapter(Activity context, ArrayList<TopBannerModel> listTopBanner) {
        this.context = context;
        this.listTopBanner = listTopBanner;
    }

    @NonNull
    @Override
    public
    Object instantiateItem(@NonNull ViewGroup container, int position) {
        TopBannerModel banner = listTopBanner.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_viettel_shop, container, false);
        ImageView imv = view.findViewById(R.id.item_banner);
        if(listTopBanner != null){
            Utilities.loadImage(context, banner.getImage(), imv);
        }
        view.setTag(position);

        container.addView(view);
        return view;
    }

    @Override
    public
    int getCount() {
        if(listTopBanner == null)
            return 0;
        return listTopBanner.size();
    }

    @Override
    public
    boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public
    void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
