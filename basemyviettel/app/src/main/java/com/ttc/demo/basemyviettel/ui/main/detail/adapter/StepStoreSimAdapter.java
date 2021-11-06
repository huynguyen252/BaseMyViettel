package com.ttc.demo.basemyviettel.ui.main.detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.utils.DeviceUtils;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.model.StepModel;
import com.ttc.demo.basemyviettel.ui.main.listener.ItemClickListener;
import com.ttc.demo.basemyviettel.utils.GlobleUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class StepStoreSimAdapter extends RecyclerView.Adapter<StepStoreSimAdapter.StepHolder> {
    List<StepModel> mListStep;
    ItemClickListener onItemStepClickListener;
    private
    Context context;
    private int mPositionClick = -1;

    public
    StepStoreSimAdapter(List<StepModel> mListStep, Context context) {
        this.mListStep = mListStep;
        this.context = context;
    }

    public void setOnStepClickListener(ItemClickListener listener){
        this.onItemStepClickListener = listener;
    }

    @NonNull
    @Override
    public
    StepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step, parent, false);

        return new StepHolder(itemView);
    }

    @Override
    public
    void onBindViewHolder(@NonNull StepHolder holder, int position) {
        StepModel stepModel = mListStep.get(position);
        holder.tvFirst.setText(Integer.toString(position + 1));
        holder.tvLast.setText(Integer.toString(position+1));
        holder.tvTitle.setText(stepModel.getTitle());
        if(position == mListStep.size() - 1){
            holder.tvLast.setVisibility(View.VISIBLE);
            holder.llLast.setVisibility(View.VISIBLE);
            holder.tvFirst.setVisibility(View.GONE);
            holder.llFirst.setVisibility(View.GONE);
            holder.vLine.setVisibility(View.GONE);
        }
        else {
            holder.tvLast.setVisibility(View.GONE);
            holder.llLast.setVisibility(View.GONE);
            holder.tvFirst.setVisibility(View.VISIBLE);
            holder.llFirst.setVisibility(View.VISIBLE);
            holder.vLine.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(v -> {
            if (mPositionClick != position) {
                mPositionClick = position;
                if (onItemStepClickListener != null) {
                    onItemStepClickListener.onItemClick(holder.itemView, stepModel, position);
                }
            }
        });

        if(!stepModel.isSelected()){
            holder.tvFirst.setBackgroundResource(R.drawable.bg_circle_unselected);
            holder.tvFirst.setTextColor(context.getResources().getColor(R.color.color_727272));
            holder.tvLast.setBackgroundResource(R.drawable.bg_circle_unselected);
            holder.tvLast.setTextColor(context.getResources().getColor(R.color.color_727272));
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.ui_button_disable));
            holder.imgFirst.setVisibility(View.GONE);
            holder.imgLast.setVisibility(View.GONE);
        } else if(stepModel.isSelected()){
            holder.tvFirst.setTextColor(context.getResources().getColor(R.color.whiteSolid));
            holder.tvFirst.setBackgroundResource(R.drawable.bg_step_selected_1);
            holder.tvLast.setTextColor(context.getResources().getColor(R.color.whiteSolid));
            holder.tvLast.setBackgroundResource(R.drawable.bg_step_selected_1);
            if(stepModel.isTextFocus()){
                holder.tvTitle.setTextColor(context.getResources().getColor(R.color.default_color_app));
                RelativeLayout.LayoutParams paramsTvFirst = (RelativeLayout.LayoutParams) holder.tvFirst.getLayoutParams();
                paramsTvFirst.width = DeviceUtils.dpToPx(context, 20);
                paramsTvFirst.height = DeviceUtils.dpToPx(context, 20);
                holder.tvFirst.setLayoutParams(paramsTvFirst);

                RelativeLayout.LayoutParams paramsTvLast = (RelativeLayout.LayoutParams) holder.tvLast.getLayoutParams();
                paramsTvLast.width = DeviceUtils.dpToPx(context, 20);
                paramsTvLast.height = DeviceUtils.dpToPx(context, 20);
                holder.tvLast.setLayoutParams(paramsTvLast);
            }
            else {
                holder.tvTitle.setTextColor(context.getResources().getColor(R.color.dustyGray));
                holder.tvFirst.setBackgroundResource(R.drawable.bg_step_unselected);

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.tvFirst.getLayoutParams();
                params.width = DeviceUtils.dpToPx(context, 20);
                params.height = DeviceUtils.dpToPx(context, 20);
                holder.tvFirst.setLayoutParams(params);

                RelativeLayout.LayoutParams paramsLast = (RelativeLayout.LayoutParams) holder.tvLast.getLayoutParams();
                paramsLast.width = DeviceUtils.dpToPx(context, 20);
                paramsLast.height = DeviceUtils.dpToPx(context, 20);
                holder.tvLast.setLayoutParams(paramsLast);
            }

            if (stepModel.isValidate()) {
                holder.imgFirst.setVisibility(View.VISIBLE);
                holder.imgLast.setVisibility(View.VISIBLE);
                holder.tvFirst.setBackgroundResource(R.drawable.brg_circle_selected_v4);
                holder.tvFirst.setTextColor(context.getResources().getColor(R.color.whiteSolid));
                holder.tvLast.setBackgroundResource(R.drawable.brg_circle_selected_v4);
                holder.tvLast.setTextColor(context.getResources().getColor(R.color.whiteSolid));
                holder.tvTitle.setTextColor(context.getResources().getColor(R.color.default_color_app));
            } else {
                holder.imgFirst.setVisibility(View.GONE);
                holder.imgLast.setVisibility(View.GONE);
            }

        }

        if (stepModel.isLineSelected()) {
            holder.vLine.setBackgroundResource(R.color.default_color_app);
        } else {
            holder.vLine.setBackgroundResource(R.color.ui_button_disable);
        }
        if (position == 0) {
            holder.vLineFirst.setVisibility(View.INVISIBLE);
        } else {
            holder.vLineFirst.setVisibility(View.VISIBLE);
            if (stepModel.isLineFirstSelected()) {
                holder.vLineFirst.setBackgroundResource(R.color.default_color_app);
            } else {
                holder.vLineFirst.setBackgroundResource(R.color.ui_button_disable);
            }
        }

        RelativeLayout.LayoutParams paramsLlFirst = (RelativeLayout.LayoutParams) holder.llFirst.getLayoutParams();
        paramsLlFirst.width = DeviceUtils.dpToPx(context, 20);
        paramsLlFirst.height = DeviceUtils.dpToPx(context, 20);
        holder.llFirst.setLayoutParams(paramsLlFirst);

        RelativeLayout.LayoutParams paramsLlLast = (RelativeLayout.LayoutParams) holder.llLast.getLayoutParams();
        paramsLlLast.width = DeviceUtils.dpToPx(context, 20);
        paramsLlLast.height = DeviceUtils.dpToPx(context, 20);
        holder.llLast.setLayoutParams(paramsLlLast);

        int widthItem;
        if (mListStep.size() > 3) {
            int width = context.getResources().getDisplayMetrics().widthPixels;
            widthItem = (width / (mListStep.size() - 1)) - GlobleUtil.convertDpToPixel(12 * 2 + 18 * 2, context);
            if (widthItem < GlobleUtil.convertDpToPixel(45, context)) {
                widthItem = GlobleUtil.convertDpToPixel(45, context);
            }
        } else {
            int width = context.getResources().getDisplayMetrics().widthPixels - 2 * GlobleUtil.convertDpToPixel(30, context); // 30 total margin left, right cua listview
            width -= mListStep.size() * GlobleUtil.convertDpToPixel(20, context); // tru di width cua icon step
            width -= mListStep.size() * GlobleUtil.convertDpToPixel(15, context); // tru di width cua v_line_first
            width -= GlobleUtil.convertDpToPixel(10, context); //  minWidth title - 40
            widthItem = (width / (mListStep.size() - 1));
        }

        RelativeLayout.LayoutParams layoutParams_pass_line = new RelativeLayout.LayoutParams(widthItem, GlobleUtil.convertDpToPixel(3, context));
        layoutParams_pass_line.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams_pass_line.addRule(RelativeLayout.RIGHT_OF, holder.llFirst.getId());
        RelativeLayout.LayoutParams layoutParams_first_line = new RelativeLayout.LayoutParams(GlobleUtil.convertDpToPixel(15, context), GlobleUtil.convertDpToPixel(3, context));
        layoutParams_first_line.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        holder.vLine.setLayoutParams(layoutParams_pass_line);
        holder.vLineFirst.setLayoutParams(layoutParams_first_line);
    }

    @Override
    public
    int getItemCount() {
        return mListStep.size();
    }

    private void controlViews(int position, boolean isShowed) {
        if (!isShowed) {
            for (int i = 0; i < mListStep.size(); i++) {
                if (i == position) {
                    mListStep.get(i).setTextFocus(true);
                    mListStep.get(i).setSelected(true);
                    mListStep.get(i).setLineSelected(false);
                    mListStep.get(i).setLineFirstSelected(true);
                    mListStep.get(i).setTextFocus(true);
                } else if (i < position) {
                    mListStep.get(i).setSelected(true);
                    mListStep.get(i).setLineSelected(true);
                    mListStep.get(i).setTextFocus(false);
                    mListStep.get(i).setLineFirstSelected(true);
                } else {
                    mListStep.get(i).setSelected(false);
                    mListStep.get(i).setTextFocus(false);
                    mListStep.get(i).setLineSelected(false);
                    mListStep.get(i).setLineFirstSelected(false);
                }
                if (position == 0) {
                    mListStep.get(position).setSelected(true);
                    mListStep.get(position).setLineSelected(false);
                    mListStep.get(position).setLineFirstSelected(false);
                    mListStep.get(position).setTextFocus(true);
                }
                notifyItemChanged(i);
            }
        }
        else {
            for (int i = 0; i < mListStep.size(); i++) {
                if (i == position) {
                    mListStep.get(i).setTextFocus(true);
                } else {
                    mListStep.get(i).setTextFocus(false);
                }
                notifyItemChanged(i);
            }
        }
    }

    public void notifyItemChangedData(int position, boolean isShowed) {
        mPositionClick = position;
        controlViews(position,isShowed);
    }

    public void notifyItemChangedDataValidate(int position, boolean isValidate) {
        for (int i = 0; i < mListStep.size(); i++) {
            if (i == position) {
                mListStep.get(i).setValidate(isValidate);
                notifyItemChanged(i);
            }
        }
    }

    class StepHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_first)
        TextView tvFirst;
        @BindView(R.id.v_line)
        View vLine;
        @BindView(R.id.v_line_first)
        View vLineFirst;
        @BindView(R.id.tv_last)
        TextView tvLast;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img_first)
        ImageView imgFirst;
        @BindView(R.id.img_last)
        ImageView imgLast;
        @BindView(R.id.ll_first)
        RelativeLayout llFirst;
        @BindView(R.id.ll_last)
        RelativeLayout llLast;
        @BindView(R.id.parent_view)
        RelativeLayout parentView;
        public
        StepHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
