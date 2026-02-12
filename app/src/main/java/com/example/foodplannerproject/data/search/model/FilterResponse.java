package com.example.foodplannerproject.data.search.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterResponse {
    @SerializedName("meals")
    private List<FilterMeal> meals;

    public List<FilterMeal> getMeals() { return meals; }
}
