package com.example.foodplannerproject.data.favorite.data_source;

import android.content.Context;

import com.example.foodplannerproject.data.database.AppDatabase;
import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public class FavoriteLocalDataSource {
    private FavoriteDao dao;

    public FavoriteLocalDataSource(Context context) {
        dao = AppDatabase.getInstance(context).FavoriteDao();
    }
    public Completable insertFavoriteMeal(FavoriteMeal meal) {
        return dao.insertFavoriteMeal(meal);
    }

    public Completable deleteFavoriteMeal(FavoriteMeal meal) {
        return dao.deleteFavoriteMeal(meal);
    }

    public Flowable<List<FavoriteMeal>> getAllFavoriteMeals() {
        return dao.getAllFavoriteMeals();
    }

    public Maybe<FavoriteMeal> getMealById(String mealId) {
        return dao.getMealById(mealId);
    }

}
