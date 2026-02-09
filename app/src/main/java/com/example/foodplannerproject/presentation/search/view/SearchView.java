package com.example.foodplannerproject.presentation.search.view;

import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.model.SearchMeal;

import java.util.List;

public interface SearchView {
    void showLoading();
    void hideLoading();
    void showError(String message);
    void showNoInternet();
    void searchByFilter(List<SearchMeal> meals);
}
