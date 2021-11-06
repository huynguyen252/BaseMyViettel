package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaDetailModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.DistrictModel;

import java.util.List;

public
class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.DistrictHolder>{
    private
    List<DistrictModel> list;
    private ItemClickDistrictListener listener;

    public
    DistrictAdapter(List<DistrictModel> list, ItemClickDistrictListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public
    DistrictHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);
        return new DistrictHolder(v);
    }

    @Override
    public
    void onBindViewHolder(@NonNull DistrictHolder holder, int position) {
        holder.tvArea.setText(list.get(position).getName());

        holder.tvArea.setOnClickListener(v -> listener.onClickItem(list.get(position)));
    }

    @Override
    public
    int getItemCount() {
        return list.size();
    }

    class DistrictHolder extends RecyclerView.ViewHolder {
        private
        TextView tvArea;
        public
        DistrictHolder(@NonNull View itemView) {
            super(itemView);
            tvArea = itemView.findViewById(R.id.item_area);
        }
    }

    public interface ItemClickDistrictListener{
        void onClickItem(DistrictModel districtModel);
    }
}
