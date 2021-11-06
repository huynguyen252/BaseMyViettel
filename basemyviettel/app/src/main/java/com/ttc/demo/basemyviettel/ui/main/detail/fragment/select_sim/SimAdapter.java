package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;
import com.ttc.demo.basemyviettel.ui.main.listener.OnBuySimClickListener;
import com.ttc.demo.basemyviettel.utils.Constants;
import com.ttc.demo.basemyviettel.utils.NumberUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class SimAdapter extends RecyclerView.Adapter<SimAdapter.SimHolder> {
    private Context mContext;
    private
    List<NumberModel> list;
    public
    Constants.ORDER_TYPE orderType;
    private
    OnBuySimClickListener listener;
    private NumberModel mSelectedNumber;
    public
    SimAdapter(Context context,
               List<NumberModel> list,
               Constants.ORDER_TYPE orderType,
               OnBuySimClickListener listener,
               NumberModel mSelectedNumber) {
        this.mContext = context;
        this.list = list;
        this.orderType = orderType;
        this.listener = listener;
        this.mSelectedNumber = mSelectedNumber;
    }

    public
    SimAdapter(Constants.ORDER_TYPE orderType) {
        this.orderType = orderType;
    }

    @NonNull
    @Override
    public
    SimHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sim, parent, false);
        return new SimHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull SimHolder holder, int position) {
        final NumberModel model = list.get(position);
        holder.mNumberTv.setText(NumberUtils.convertVietNamPhoneNumber(model.getIsdn()));
        if(orderType == Constants.ORDER_TYPE.PRE){
            if(!model.getPrePrice().isEmpty()){
                holder.mPriceTv.setText(NumberUtils.formatPriceNumber(Integer.valueOf(model.getPrePrice()))+" đ");
            }
        }
        else if(orderType == Constants.ORDER_TYPE.POS){
            if(!model.getPosPrice().isEmpty()){
                holder.mPriceTv.setText(NumberUtils.formatPriceNumber(Integer.valueOf(model.getPosPrice()))+" đ");
            }
        }
        holder.itemView.setOnClickListener(v -> {
            if(mSelectedNumber == null || !model.getIsdn().equals(mSelectedNumber.getIsdn())){
                mSelectedNumber = model;
                for (int i=0;i<list.size();i++){
                    list.get(i).setSelected(false);
                }
                notifyDataSetChanged();
                list.get(position).setSelected(true);
                notifyItemChanged(position);
                listener.onBuyClickedListener(model);
            }
        });
        if (model.isSelected()){
            holder.imgSelectItem.setImageResource(R.drawable.ic_radio_button_active);
        }else {
            holder.imgSelectItem.setImageResource(R.drawable.ic_radio_button_default);
        }
    }

    @Override
    public
    int getItemCount() {
        return list.size();
    }

    class SimHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_sim_number_tv)
        TextView mNumberTv;
        @BindView(R.id.item_sim_price_tv)
        TextView mPriceTv;
        @BindView(R.id.imgSelectItem)
        AppCompatImageView imgSelectItem;
        public
        SimHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
