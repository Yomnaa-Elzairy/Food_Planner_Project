package com.example.foodplannerproject.presentation.home.view;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.meal.model.Meal;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void showError(String message);
    void showNoInternet();
    void showRandomMeal(Meal meal);
    void showAllCategories(List<Category> categories);
}
