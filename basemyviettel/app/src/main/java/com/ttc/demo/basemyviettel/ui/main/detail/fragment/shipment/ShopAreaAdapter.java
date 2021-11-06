package com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ShopAreaModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class ShopAreaAdapter extends RecyclerView.Adapter<ShopAreaAdapter.ShopHolder>{
    private
    List<ShopAreaModel> list;

    public
    ShopAreaAdapter(List<ShopAreaModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public
    ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_area, parent, false);
        return new ShopHolder(v);
    }

    @Override
    public
    void onBindViewHolder(@NonNull ShopHolder holder, int position) {
        holder.tvShopName.setText(list.get(position).getShopFullName());
        holder.tvShopAddress.setText(list.get(position).getAddress());
    }

    @Override
    public
    int getItemCount() {
        return list.size();
    }

    class ShopHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_shop_address)
        TextView tvShopAddress;
        public
        ShopHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
