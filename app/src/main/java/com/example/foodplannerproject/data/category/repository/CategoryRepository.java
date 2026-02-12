package com.example.foodplannerproject.data.category.repository;

import com.example.foodplannerproject.data.category.data_source.CategoryRemoteDataSource;
import com.example.foodplannerproject.data.category.data_source.CategoryRemoteResponse;
import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.model.CategoryResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CategoryRepository {
    private CategoryRemoteDataSource categoryRemoteDataSource;

    public CategoryRepository() {
        this.categoryRemoteDataSource = new CategoryRemoteDataSource();
    }

    public Single<List<Category>> getCategories() {
        return categoryRemoteDataSource.getAllCategories();
    }

}
