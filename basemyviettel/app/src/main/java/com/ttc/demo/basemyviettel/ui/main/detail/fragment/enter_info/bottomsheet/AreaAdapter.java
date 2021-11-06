package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaModel;

import java.util.List;

public
class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaHolder>{
    private List<AreaModel> list;
    private ItemClickListener listener;

    public
    AreaAdapter(List<AreaModel> list, ItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public
    AreaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);
        return new AreaHolder(v);
    }

    @Override
    public
    void onBindViewHolder(@NonNull AreaHolder holder, int position) {
        holder.tvArea.setText(list.get(position).getName());

        holder.tvArea.setOnClickListener(v -> listener.onClickItem(list.get(position)));
    }

    @Override
    public
    int getItemCount() {
        return list.size();
    }

    class AreaHolder extends RecyclerView.ViewHolder {
        private
        TextView tvArea;
        public
        AreaHolder(@NonNull View itemView) {
            super(itemView);
            tvArea = itemView.findViewById(R.id.item_area);
        }
    }

    public interface ItemClickListener{
        void onClickItem(AreaModel areaModel);
    }
}
