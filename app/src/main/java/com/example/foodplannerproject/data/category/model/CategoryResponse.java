package com.example.foodplannerproject.data.category.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CategoryResponse {
    @SerializedName("categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
