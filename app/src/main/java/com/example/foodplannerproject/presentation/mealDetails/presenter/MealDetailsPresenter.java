package com.example.foodplannerproject.presentation.mealDetails.presenter;

import com.example.foodplannerproject.data.meal.model.Meal;

public interface MealDetailsPresenter {
    public void getMealDetailsById(String id);
    public void addToPlanner(Meal meal , String date);
}
