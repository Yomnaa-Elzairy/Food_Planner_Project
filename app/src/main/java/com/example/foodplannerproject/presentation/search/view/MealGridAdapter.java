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
import com.example.foodplannerproject.data.search.model.FilterMeal;

import java.util.ArrayList;
import java.util.List;

public class MealGridAdapter extends RecyclerView.Adapter<MealGridAdapter.ViewHolder> {

    private List<?> meals;
   private OnMealClickListener listener;
    public MealGridAdapter(OnMealClickListener listener) {
        this.listener = listener;
    }

    public void setMeals(List<?> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object item = meals.get(position);

        String name;
        String image;
        String mealId;


        if (item instanceof Meal) {
            name = ((Meal) item).getName();
            image = ((Meal) item).getImage();
            mealId = ((Meal) item).getId();
        } else {
            name = ((FilterMeal) item).getStrMeal();
            image = ((FilterMeal) item).getStrMealThumb();
            mealId = ((FilterMeal) item).getIdMeal();
        }

        holder.name.setText(name);
        Glide.with(holder.image.getContext()).load(image).into(holder.image);
        ;
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMealClick(mealId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals == null ? 0 : meals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgMeal);
            name = itemView.findViewById(R.id.txtMealName);
        }
    }
}