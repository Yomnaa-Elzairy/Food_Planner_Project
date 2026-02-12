package com.example.foodplannerproject.presentation.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.meal.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MealViewHolder> {

    public interface OnMealClickListener {
        void onMealClick(String id);
    }

    private List<Meal> meals = new ArrayList<>();
    private final OnMealClickListener listener;

    public SearchAdapter(OnMealClickListener listener) {
        this.listener = listener;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);

        holder.name.setText(meal.getName());

        Glide.with(holder.itemView.getContext())
                .load(meal.getImage())
                .into(holder.image);

        holder.itemView.setOnClickListener(v ->
                listener.onMealClick(meal.getId())
        );
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtMealName);
            image = itemView.findViewById(R.id.imgMeal);
        }
    }
}