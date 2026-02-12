package com.example.foodplannerproject.data.search.data_source;

import com.example.foodplannerproject.data.category.model.CategoryResponse;
import com.example.foodplannerproject.data.network.ApiService;
import com.example.foodplannerproject.data.network.RetrofitClient;
import com.example.foodplannerproject.data.search.model.AreaResponse;
import com.example.foodplannerproject.data.search.model.FilterResponse;
import com.example.foodplannerproject.data.search.model.IngredientResponse;
import com.example.foodplannerproject.data.search.model.SearchResponse;

import io.reactivex.rxjava3.core.Single;

public class SearchRemoteDataSource {

    private final ApiService apiService;

    public SearchRemoteDataSource() {
        apiService = RetrofitClient.getInstance()
                .create(ApiService.class);;
    }

    public Single<SearchResponse> searchByName(String name) {
        return apiService.searchByName(name);
    }

    public Single<AreaResponse> getAreas() {
        return apiService.getAreas("list");
    }

    public Single<IngredientResponse> getIngredients() {
        return apiService.getIngredients("list");
    }
    public Single<CategoryResponse> getCategories(){return apiService.getAllCategories();}

    public Single<FilterResponse> filterByCategory(String category) {
        return apiService.filterByCategory(category);
    }

    public Single<FilterResponse> filterByArea(String area) {
        return apiService.filterByArea(area);
    }

    public Single<FilterResponse> filterByIngredient(String ingredient) {
        return apiService.filterByIngredient(ingredient);
    }
}