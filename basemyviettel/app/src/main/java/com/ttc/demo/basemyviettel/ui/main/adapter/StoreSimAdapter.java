package com.ttc.demo.basemyviettel.ui.main.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.utils.DeviceUtils;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.model.SimModel;
import com.ttc.demo.basemyviettel.utils.NumberUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class StoreSimAdapter extends RecyclerView.Adapter<StoreSimAdapter.SimHolder> {
    private Activity context;
    private
    ArrayList<SimModel> listSim;

    public
    StoreSimAdapter(Activity context, ArrayList<SimModel> listSim) {
        this.context = context;
        this.listSim = listSim;
    }

    @NonNull
    @Override
    public
    SimHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_store_sim, parent, false);
        return new SimHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull SimHolder holder, int position) {
        holder.number.setText(NumberUtils.convertVietNamPhoneNumber(listSim.get(position).getIsdn()));
        holder.price.setText(NumberUtils.formatPriceNumber(Integer.valueOf(listSim.get(position).getPre_price()))+" đ");

        int width = (int) (DeviceUtils.getDeviceSize(context).x * 0.77f);
        holder.itemView.getLayoutParams().width = width;
        if (position >= (getItemCount() / 3) * 3) {
            holder.line.setVisibility(View.GONE);
        }
    }

    @Override
    public
    int getItemCount() {
        return Math.min(listSim.size(), 13);
    }

    class SimHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sim)
        TextView number;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.line)
        View line;
        public
        SimHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
