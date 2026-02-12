package com.example.foodplannerproject.data.search.repository;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.model.CategoryResponse;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.data_source.SearchRemoteDataSource;
import com.example.foodplannerproject.data.search.data_source.SearchRemoteResponse;
import com.example.foodplannerproject.data.search.model.Area;
import com.example.foodplannerproject.data.search.model.AreaResponse;
import com.example.foodplannerproject.data.search.model.FilterMeal;
import com.example.foodplannerproject.data.search.model.FilterResponse;
import com.example.foodplannerproject.data.search.model.Ingredient;
import com.example.foodplannerproject.data.search.model.IngredientResponse;
import com.example.foodplannerproject.data.search.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class SearchRepository {

    private final SearchRemoteDataSource remoteDataSource;

    public SearchRepository() {
        remoteDataSource = new SearchRemoteDataSource();
    }

    public Single<List<String>> getAreas() {
        return remoteDataSource.getAreas()
                .map(response -> {
                    List<String> list = new ArrayList<>();
                    for (Area area : response.getAreas()) {
                        list.add(area.getStrArea());
                    }
                    return list;
                });
    }

    public Single<List<String>> getIngredients() {
        return remoteDataSource.getIngredients()
                .map(response -> {
                    List<String> list = new ArrayList<>();
                    for (Ingredient ingredient : response.getIngredients()) {
                        list.add(ingredient.getStrIngredient());
                    }
                    return list;
                });
    }
    public Single<List<String>> getCategories() {
        return remoteDataSource.getCategories()
                .map(response -> {
                    List<String> list = new ArrayList<>();
                    for (Category category : response.getCategories()) {
                        list.add(category.getCategory());
                    }
                    return list;
                });
    }

    // ---------- FILTER MEALS ----------

    public Single<List<FilterMeal>> filterByCategory(String category) {
        return remoteDataSource.filterByCategory(category)
                .map(FilterResponse::getMeals);
    }

    public Single<List<FilterMeal>> filterByArea(String area) {
        return remoteDataSource.filterByArea(area)
                .map(FilterResponse::getMeals);
    }

    public Single<List<FilterMeal>> filterByIngredient(String ingredient) {
        return remoteDataSource.filterByIngredient(ingredient)
                .map(FilterResponse::getMeals);
    }

    // ---------- SEARCH BY NAME ----------

    public Single<List<Meal>> searchByName(String name) {
        return remoteDataSource.searchByName(name)
                .map(SearchResponse::getMeals);
    }
}
