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
import com.ttc.demo.basemyviettel.ui.main.listener.OnMobilePackageClickListener;
import com.ttc.demo.basemyviettel.utils.NumberUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MobileHolder> {
    private
    Context mContext;
    private
    List<MobilePackage> list;
    private MobilePackage mMobileSeleted;
    private
    OnMobilePackageClickListener listener;

    public
    MobileAdapter(Context mContext,
                  List<MobilePackage> list,
                  OnMobilePackageClickListener listener,
                  MobilePackage mobilePackage) {
        this.mContext = mContext;
        this.list = list;
        this.listener = listener;
        this.mMobileSeleted = mobilePackage;
    }

    @NonNull
    @Override
    public
    MobileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mobile_package_vtshop_detail, parent, false);
        return new MobileHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull MobileHolder holder, int position) {
        final MobilePackage model = list.get(position);
        if (!model.getShort_name().isEmpty()){
            holder.mTitle.setText(model.getShort_name());
        }
        if (!model.getShort_description().isEmpty()){
            holder.mDescription.setText(model.getShort_description());
        }
        holder.tvPrice.setText(NumberUtils.formatPriceNumber(model.getPrice())+"đ");

        holder.itemView.setOnClickListener(v -> {
            if(mMobileSeleted == null || !model.getService_code().equals(mMobileSeleted.getService_code())){
                mMobileSeleted = model;
                for (int i=0;i<list.size();i++){
                    list.get(i).setSelected(false);
                }
                notifyDataSetChanged();
                list.get(position).setSelected(true);
                notifyItemChanged(position);
                listener.onMobileSelectedClick(model);
            }
        });

        if (model.isSelected()) {
            holder.mRootLayout.setBackgroundResource(R.drawable.bg_red);
        } else {
            holder.mRootLayout.setBackgroundResource(R.drawable.bg_white);
        }

        holder.mButton.setOnClickListener(v -> {
            listener.onMobileDetailClick(model);
        });
    }

    @Override
    public
    int getItemCount() {
        return Math.min(list.size(), 3);
    }

    class MobileHolder extends RecyclerView.ViewHolder {
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
        MobileHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
