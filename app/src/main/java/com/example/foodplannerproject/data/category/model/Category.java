package com.example.foodplannerproject.data.category.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("idCategory")
    private int id;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strCategoryThumb")
    private String image;

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
