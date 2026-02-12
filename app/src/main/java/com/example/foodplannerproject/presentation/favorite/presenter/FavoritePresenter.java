package com.example.foodplannerproject.presentation.favorite.presenter;

import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;

public interface FavoritePresenter {
        void getAllFavorites();
        void deleteFavorite(FavoriteMeal meal);
    }


