package com.example.foodplannerproject.data.meal.data_source;

import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.meal.model.MealResponse;
import com.example.foodplannerproject.data.network.ApiService;
import com.example.foodplannerproject.data.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRemoteDataSource {
    private ApiService mealApiService;

    public MealRemoteDataSource() {
        mealApiService = RetrofitClient.getInstance()
                .create(ApiService.class);
    }

    public void getRandomMeal(MealRemoteResponse<Meal> callback){
        mealApiService.getRandomMeal().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Meal meal = response.body().getMeals().get(0);
                    callback.onSuccess(meal);
                }
                else callback.onFailure("No meal found");
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());

            }
        });
    }
    public void getMealBId(String id,MealRemoteResponse<Meal> callback){
        mealApiService.getMealByID(id).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Meal meal = response.body().getMeals().get(0);
                    callback.onSuccess(meal);
                }
                else callback.onFailure("No meal found");
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
