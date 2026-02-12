package com.example.foodplannerproject.presentation.mealDetails.view;

import com.example.foodplannerproject.data.meal.model.Meal;

public interface MealDetailsView {
    void showLoading();
    void showMessage(String msg);
    void hideLoading();
    void showError(String message);
    void showNoInternet();
    void getMealById(Meal meal);
    void showFavoriteState(boolean isFavorite);
    void onMealAddedToFavorite();
    void onMealRemovedFromFavorite();
    void showMealVideo(String videoId);
}
