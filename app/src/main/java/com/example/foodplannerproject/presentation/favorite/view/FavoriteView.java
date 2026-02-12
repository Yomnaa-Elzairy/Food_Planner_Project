package com.example.foodplannerproject.presentation.favorite.view;

import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;

import java.util.List;

public interface FavoriteView {
    void showFavorites(List<FavoriteMeal> meals);
    void showError(String msg);
}
