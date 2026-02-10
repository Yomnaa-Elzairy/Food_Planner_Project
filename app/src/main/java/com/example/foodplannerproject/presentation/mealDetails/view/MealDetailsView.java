package com.example.foodplannerproject.presentation.mealDetails.view;

import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.model.SearchMeal;

import java.util.List;

public interface MealDetailsView {
    void showLoading();
    void hideLoading();
    void showError(String message);
    void showNoInternet();
    void getMealById(Meal meal);
}
