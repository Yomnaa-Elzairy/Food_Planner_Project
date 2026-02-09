package com.example.foodplannerproject.data.search.model;

import com.example.foodplannerproject.data.meal.model.Meal;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("meals")
    private List<SearchMeal> meals;

    public List<SearchMeal> getMeals() {
        return meals;
    }
}
