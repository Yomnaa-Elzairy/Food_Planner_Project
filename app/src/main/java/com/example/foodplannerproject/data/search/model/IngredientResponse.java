package com.example.foodplannerproject.data.search.model;

import com.example.foodplannerproject.data.meal.model.Ingredients;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {

    @SerializedName("meals")
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}

