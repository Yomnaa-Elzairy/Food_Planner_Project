package com.example.foodplannerproject.presentation.mealDetails.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerproject.R;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private List<String> stepsList = new ArrayList<>();

    public void setList(List<String> list) {
        this.stepsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        String step = stepsList.get(position);

        holder.tvStepTitle.setText("Step " + (position + 1));
        holder.tvStepDesc.setText(step);
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    static class StepViewHolder extends RecyclerView.ViewHolder {

        TextView tvStepNumber, tvStepTitle, tvStepDesc;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStepTitle = itemView.findViewById(R.id.tvStepTitle);
            tvStepDesc = itemView.findViewById(R.id.tvStepDesc);
        }
    }
}
