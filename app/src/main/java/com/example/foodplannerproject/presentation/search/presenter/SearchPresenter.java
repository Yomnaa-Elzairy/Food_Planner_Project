package com.example.foodplannerproject.presentation.search.presenter;

public interface SearchPresenter {


    void getAreas();
    void getIngredients();
    void getCategories();

    void filterByCategory(String category);
    void filterByArea(String area);
    void filterByIngredient(String ingredient);

    void searchByName(String name);

    void detachView();
}