package com.example.foodplannerproject.presentation.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.search.model.Area;
import com.example.foodplannerproject.data.search.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    public interface OnFilterClickListener {
        void onFilterClick(String value);
    }

    private List<String> items = new ArrayList<>();
    private OnFilterClickListener listener;

    public FilterAdapter(OnFilterClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String value = items.get(position);
        holder.textView.setText(value);
        holder.itemView.setOnClickListener(v -> listener.onFilterClick(value));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtFilter);
        }
    }
}