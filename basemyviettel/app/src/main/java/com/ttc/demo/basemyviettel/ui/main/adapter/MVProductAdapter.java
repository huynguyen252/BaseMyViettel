package com.ttc.demo.basemyviettel.ui.main.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.utils.DeviceUtils;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVInfoModel;
import com.ttc.demo.basemyviettel.utils.NumberUtils;
import com.ttc.demo.basemyviettel.utils.Utilities;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class MVProductAdapter extends RecyclerView.Adapter<MVProductAdapter.ProductHolder>{
    private
    Activity context;
    private
    ArrayList<MVInfoModel> list;
    //  nếu sizeItem != -1  : số lương item hiển thị trên màn hình nếu adapter vuốt ngang
    private int sizeItem = -1;
    // nếu maxItem != -1 thì sẽ hiển thị số item bằng với maxItem,
    private int maxItem = -1;

    public
    MVProductAdapter(Activity context, ArrayList<MVInfoModel> list, int sizeItem, int maxItem) {
        this.context = context;
        this.list = list;
        this.sizeItem = sizeItem;
        this.maxItem = maxItem;
    }

    @NonNull
    @Override
    public
    ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_vtshop, parent, false);
        if (-1  != sizeItem) {
            int width = (int) (DeviceUtils.getDeviceSize(context).x /  sizeItem);
            view.getLayoutParams().width = width;
        }
        return new ProductHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        MVInfoModel model = list.get(position);
        if(!model.getImage().isEmpty()){
            try {
                Utilities.loadImage(context, model.getImage(), holder.img);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(!model.getTitle().isEmpty()){
            holder.name.setText(model.getTitle());
        }
        String price = Utilities.isNullOrEmpty(model.getSalePrice()) ? "" : model.getPrice();
        holder.basePrice.setText(NumberUtils.formatPriceNumber(Integer.valueOf(price))+"đ");
        holder.basePrice.setPaintFlags(holder.basePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if(Utilities.isNullOrEmpty(price)){
            holder.basePrice.setVisibility(View.GONE);
        }
        else{
            holder.basePrice.setVisibility(View.VISIBLE);
        }
        String salePrice = Utilities.isNullOrEmpty(model.getSalePrice()) ? model.getPrice() : model.getSalePrice();
        holder.proPrice.setText(NumberUtils.formatPriceNumber(Integer.valueOf(salePrice))+"đ");

        if(!Utilities.isNullOrEmpty(model.getDiscountPercent())){
            holder.discount.setText(model.getDiscountPercent()+"%");
            holder.discount.setVisibility(View.VISIBLE);
        }
        else {
            holder.discount.setVisibility(View.GONE);
        }
    }

    @Override
    public
    int getItemCount() {
        if (list != null) {
            if (-1 != maxItem  && list.size() > maxItem) {
                return maxItem;
            } else {
                return list.size();
            }
        } else {
            return 0;
        }
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView img;
        @BindView(R.id.tv_discount)
        TextView discount;
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_promotion_price)
        TextView proPrice;
        @BindView(R.id.tv_base_price)
        TextView basePrice;

        public
        ProductHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
