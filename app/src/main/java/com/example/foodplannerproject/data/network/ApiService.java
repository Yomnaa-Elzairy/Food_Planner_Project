package com.example.foodplannerproject.data.network;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.model.CategoryResponse;
import com.example.foodplannerproject.data.meal.model.MealResponse;
import com.example.foodplannerproject.data.search.model.AreaResponse;
import com.example.foodplannerproject.data.search.model.FilterResponse;
import com.example.foodplannerproject.data.search.model.IngredientResponse;
import com.example.foodplannerproject.data.search.model.SearchResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();
    @GET("categories.php")
    Single<CategoryResponse> getAllCategories();
    @GET("search.php")
    Single<SearchResponse> searchByName(@Query("s") String mealName);

    // list filters
//    @GET("list.php")
//    Single<CategoryResponse> getCategories(@Query("c") String list);

    @GET("list.php")
    Single<AreaResponse> getAreas(@Query("a") String list);

    @GET("list.php")
    Single<IngredientResponse> getIngredients(@Query("i") String list);

    // filter meals
    @GET("filter.php")
    Single<FilterResponse> filterByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<FilterResponse> filterByArea(@Query("a") String area);

    @GET("filter.php")
    Single<FilterResponse> filterByIngredient(@Query("i") String ingredient);
    @GET("lookup.php")
    Call<MealResponse> getMealByID(@Query("i") String id);
}
