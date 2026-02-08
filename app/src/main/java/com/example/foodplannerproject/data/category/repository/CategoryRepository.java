package com.example.foodplannerproject.data.category.repository;

import com.example.foodplannerproject.data.category.data_source.CategoryRemoteDataSource;
import com.example.foodplannerproject.data.category.data_source.CategoryRemoteResponse;
import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.model.CategoryResponse;

public class CategoryRepository {
    private CategoryRemoteDataSource categoryRemoteDataSource;

    public CategoryRepository() {
        this.categoryRemoteDataSource = new CategoryRemoteDataSource();
    }

    public void getAllCategories(CategoryRemoteResponse<Category> callback){
        categoryRemoteDataSource.getAllCategories(callback);
    }
}
