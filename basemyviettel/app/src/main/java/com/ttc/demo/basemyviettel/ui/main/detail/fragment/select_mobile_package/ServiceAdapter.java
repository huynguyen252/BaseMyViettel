package com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.model.MobilePackage;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ServicePackage;
import com.ttc.demo.basemyviettel.ui.main.listener.OnVasPackageClickListener;
import com.ttc.demo.basemyviettel.utils.NumberUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceHolder>{

    private
    Context mContext;
    private
    List<ServicePackage> list;
    private
    OnVasPackageClickListener listener;
    private ServicePackage mVasSelected;

    public
    ServiceAdapter(Context mContext, List<ServicePackage> list, OnVasPackageClickListener listener, ServicePackage servicePackage) {
        this.mContext = mContext;
        this.list = list;
        this.listener = listener;
        this.mVasSelected = servicePackage;
    }

    @NonNull
    @Override
    public
    ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vas_package_vtshop_detail, parent, false);
        return new ServiceHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull ServiceHolder holder, int position) {
        final ServicePackage model = list.get(position);
        if (!model.getShort_name().isEmpty()){
            holder.mTitle.setText(model.getShort_name());
        }
        if (!model.getShort_description().isEmpty()){
            holder.mDescription.setText(model.getShort_description());
        }
        holder.tvPrice.setText(NumberUtils.formatPriceNumber(model.getPrice())+"đ");

        holder.itemView.setOnClickListener(v -> {
            if(mVasSelected == null || !model.getService_code().equals(mVasSelected.getService_code())){
                mVasSelected = model;
                for (int i=0;i<list.size();i++){
                    list.get(i).setSelected(false);
                }
                notifyDataSetChanged();
                list.get(position).setSelected(true);
                notifyItemChanged(position);
                listener.onVasItemClick(model);
            }
        });

        if (model.isSelected()) {
            holder.mRootLayout.setBackgroundResource(R.drawable.bg_red);
        } else {
            holder.mRootLayout.setBackgroundResource(R.drawable.bg_white);
        }
        holder.mButton.setOnClickListener(v -> {
            listener.onVasDetailClick(model);
        });
    }

    @Override
    public
    int getItemCount() {
        return Math.min(list.size(), 3);
    }

    class ServiceHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTitle;
        @BindView(R.id.tvDescription)
        TextView mDescription;
        @BindView(R.id.item_service_chi_tiet)
        TextView mButton;
        @BindView(R.id.card_view)
        View mRootLayout;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        public
        ServiceHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
