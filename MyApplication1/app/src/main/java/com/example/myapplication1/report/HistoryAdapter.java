package com.example.myapplication1.report;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.source.History;
import com.example.myapplication1.views.RobotoBoldTextView;
import com.example.myapplication1.views.RobotoRegularTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gautam on 13/07/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private List<History> HistoryList;

    HistoryAdapter(List<History> historyList) {
        setList(historyList);
    }

    void replaceData(List<History> tasks) {
        setList(tasks);
        notifyDataSetChanged();
    }

    private void setList(List<History> historyList) {
        this.HistoryList = historyList;
    }


    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History history = HistoryList.get(position);
        if (history == null) {
            return;
        }
        holder.tvMedDate.setText(history.getFormattedDate());
        setMedicineAction(holder, history.getAction());
        holder.tvMedicineName.setText(history.getPillName());
        holder.tvDoseDetails.setText(history.getFormattedDose());

    }

    private void setMedicineAction(HistoryViewHolder holder, int action) {
        switch (action) {
            case 0:
            default:
                holder.ivMedicineAction.setVisibility(View.GONE);
                break;
            case 1:
                holder.ivMedicineAction.setVisibility(View.VISIBLE);
                holder.ivMedicineAction.setImageResource(R.drawable.image_reminder_taken);
                break;
            case 2:
                holder.ivMedicineAction.setImageResource(R.drawable.image_reminder_not_taken);
                holder.ivMedicineAction.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return (HistoryList != null && !HistoryList.isEmpty()) ? HistoryList.size() : 0;
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_med_date)
        RobotoBoldTextView tvMedDate;

        @BindView(R.id.tv_medicine_name)
        RobotoBoldTextView tvMedicineName;

        @BindView(R.id.tv_dose_details)
        RobotoRegularTextView tvDoseDetails;

        @BindView(R.id.iv_medicine_action)
        ImageView ivMedicineAction;

        HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
