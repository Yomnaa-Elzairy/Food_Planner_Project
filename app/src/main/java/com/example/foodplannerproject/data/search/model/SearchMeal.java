package com.example.foodplannerproject.data.search.model;

import com.google.gson.annotations.SerializedName;

public class SearchMeal {
    @SerializedName("idMeal")
    String id;
    @SerializedName("strMeal")
    String name;
    @SerializedName("strMealThumb")
    String image;

    public String getName() { return name; }
    public String getImage() { return image; }
}
