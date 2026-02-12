package com.example.foodplannerproject.presentation.search.view;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.meal.model.Ingredients;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.model.Area;
import com.example.foodplannerproject.data.search.model.FilterMeal;
import com.example.foodplannerproject.data.search.model.Ingredient;

import java.util.List;

public interface SearchView {


    void showAreas(List<String> areas);
    void showIngredients(List<String> ingredients);
    void showCategories(List<String> categories);

    void showFilteredMeals(List<FilterMeal> meals);
    void showSearchedMeals(List<Meal> meals);

    void showError(String message);
    void showLoading();
    void hideLoading();
}

