package com.example.foodplannerproject.data.category.data_source;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.model.CategoryResponse;
import com.example.foodplannerproject.data.network.ApiService;
import com.example.foodplannerproject.data.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CategoryRemoteDataSource {
    private ApiService apiService;

    public CategoryRemoteDataSource() {
        apiService = RetrofitClient.getInstance()
                .create(ApiService.class);
    }

    public Single<List<Category>> getAllCategories() {
        return apiService.getAllCategories()
                .map(CategoryResponse::getCategories);
    }


}
