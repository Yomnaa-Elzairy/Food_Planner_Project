package com.example.foodplannerproject.data.favorite.repository;


import android.content.Context;

import com.example.foodplannerproject.data.favorite.data_source.FavoriteLocalDataSource;
import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public class FavoriteRepository {

    private FavoriteLocalDataSource localDataSource;

    public FavoriteRepository(Context context) {
        this.localDataSource = new FavoriteLocalDataSource(context);
    }

    public Completable insertFavoriteMeal(FavoriteMeal meal) {
        return localDataSource.insertFavoriteMeal(meal);
    }

    public Completable deleteFavoriteMeal(FavoriteMeal meal) {
        return localDataSource.deleteFavoriteMeal(meal);
    }

    public Flowable<List<FavoriteMeal>> getAllFavoriteMeals() {
        return localDataSource.getAllFavoriteMeals();
    }

    public Maybe<FavoriteMeal> getMealById(String mealId) {
        return localDataSource.getMealById(mealId);
    }
}

