package com.example.foodplannerproject.data.network;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.model.CategoryResponse;
import com.example.foodplannerproject.data.meal.model.MealResponse;
import com.example.foodplannerproject.data.search.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();
    @GET("categories.php")
    Call<CategoryResponse> getAllCategories();
    @GET("filter.php")
    Call<SearchResponse> searchByFilter( @Query("i") String ingredient,
                                         @Query("c") String category,
                                         @Query("a") String area);
}
