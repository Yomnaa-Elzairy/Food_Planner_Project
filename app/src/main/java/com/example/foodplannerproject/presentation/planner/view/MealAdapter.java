package com.example.foodplannerproject.presentation.planner.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.Holder> {

    private List<PlannerMeal> meals;

    public MealAdapter(List<PlannerMeal> meals) {
        this.meals = meals;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView img;

        Holder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvMealName);
            img = itemView.findViewById(R.id.imgMeal);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal_row, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        PlannerMeal meal = meals.get(position);
        holder.tvName.setText(meal.getName());

        Glide.with(holder.itemView.getContext())
                .load(meal.getImage())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}

