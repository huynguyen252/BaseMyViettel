package com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaModel;
import com.ttc.demo.basemyviettel.ui.main.detail.model.DistrictModel;

import java.util.List;

public
class BottomDistrictFragment extends BottomSheetDialogFragment {
    private
    List<DistrictModel> list;
    private
    DistrictAdapter.ItemClickDistrictListener listener;

    public
    BottomDistrictFragment(List<DistrictModel> list, DistrictAdapter.ItemClickDistrictListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public
    Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomDistrict = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet, null, false);
        bottomDistrict.setContentView(v);
        TextView title = v.findViewById(R.id.tv_title);
        title.setText("Chọn quận/huyện");
        RecyclerView rev = v.findViewById(R.id.rev_bottom_sheet);
        rev.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        DistrictAdapter adapter = new DistrictAdapter(list, new DistrictAdapter.ItemClickDistrictListener() {
            @Override
            public
            void onClickItem(DistrictModel districtModel) {
                listener.onClickItem(districtModel);
                bottomDistrict.dismiss();
            }
        });
        rev.setAdapter(adapter);
        RecyclerView.ItemDecoration  itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rev.addItemDecoration(itemDecoration);
        return bottomDistrict;
    }
}
