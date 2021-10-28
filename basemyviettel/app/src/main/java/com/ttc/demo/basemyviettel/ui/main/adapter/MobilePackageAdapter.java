package com.ttc.demo.basemyviettel.ui.main.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.model.sim.MobileModel;
import com.ttc.demo.basemyviettel.utils.NumberUtils;
import com.ttc.demo.basemyviettel.utils.Utilities;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class MobilePackageAdapter extends RecyclerView.Adapter<MobilePackageAdapter.MobileHolder>{
    private
    ArrayList<MobileModel> list;
    private
    Activity context;

    public
    MobilePackageAdapter(ArrayList<MobileModel> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public
    MobileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mobile_package, parent, false);
        return new MobileHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull MobileHolder holder, int position) {
        MobileModel model = list.get(position);
        if(!model.getShort_name().isEmpty()){
            holder.name.setText(model.getShort_name());
        }
        if(!model.getShort_description().isEmpty()){
            holder.description.setText(model.getShort_description());
        }
        holder.price.setText(NumberUtils.formatPriceNumber(model.getPrice())+" đ");
        if(!model.getImage().isEmpty()){
            try {
                Utilities.loadImage(context, model.getImage(), holder.img);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public
    int getItemCount() {
        return Math.min(list.size(), 7);
    }

    class MobileHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_mobile_package)
        ImageView img;
        @BindView(R.id.name_tv)
        TextView name;
        @BindView(R.id.price_tv)
        TextView price;
        @BindView(R.id.register_btn)
        TextView register_btn;
        @BindView(R.id.desc_tv)
        TextView description;
        public
        MobileHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
