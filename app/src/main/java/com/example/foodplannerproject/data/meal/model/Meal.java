package com.example.foodplannerproject.data.meal.model;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

public class Meal {
    @SerializedName("idMeal")
    private String id;
    @SerializedName("strMeal")
    private String name;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strArea")
    private String country;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImage() {
        return image;
    }


}
