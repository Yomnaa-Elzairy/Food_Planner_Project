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
import com.example.foodplannerproject.data.search.model.SearchItem;
import com.example.foodplannerproject.data.search.model.SearchMeal;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    List<SearchMeal> meals = new ArrayList<>();
    private OnMealClickListener listener;
    public SearchAdapter(OnMealClickListener listener) {
        this.listener = listener;
    }

    public void setMeals(List<SearchMeal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgCategory);
            name = itemView.findViewById(R.id.txtCategoryName);
        }
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_items,parent,false);
            return new SearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchMeal meal = meals.get(position);
        holder.name.setText(meal.getName());
        Glide.with(holder.itemView.getContext())
                .load(meal.getImage())
                .into(holder.image);
        holder.itemView.setOnClickListener(v -> {
            listener.onMealClick(meal.getId());
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


}
