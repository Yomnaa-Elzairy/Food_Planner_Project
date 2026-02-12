package com.example.foodplannerproject.data.meal.repository;

import com.example.foodplannerproject.data.meal.data_source.MealRemoteDataSource;
import com.example.foodplannerproject.data.meal.data_source.MealRemoteResponse;
import com.example.foodplannerproject.data.meal.model.Meal;

public class MealRepository {

    private MealRemoteDataSource mealRemoteDataSource;

    public MealRepository() {
        this.mealRemoteDataSource = new MealRemoteDataSource();
    }
    public void getRandomMeal(MealRemoteResponse<Meal> callback){
        mealRemoteDataSource.getRandomMeal(callback);

    }
    public void getMealById(String id, MealRemoteResponse<Meal> callback){
        mealRemoteDataSource.getMealBId(id,callback);
    }
}
