package com.example.foodplannerproject.presentation.planner.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.presentation.planner.model.DayGroup;

import java.util.ArrayList;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayHolder> {

    private List<DayGroup> days = new ArrayList<>();

    public void setData(List<DayGroup> list) {
        days = list;
        notifyDataSetChanged();
    }

    class DayHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        RecyclerView rvMeals;

        DayHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvMealType);
            rvMeals = itemView.findViewById(R.id.rvDayMeals);
        }
    }

    @NonNull
    @Override
    public DayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day_card, parent, false);
        return new DayHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayHolder holder, int position) {
        DayGroup day = days.get(position);
        holder.tvDate.setText(day.date);

        MealAdapter adapter = new MealAdapter(day.meals);
        holder.rvMeals.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.rvMeals.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }
}

