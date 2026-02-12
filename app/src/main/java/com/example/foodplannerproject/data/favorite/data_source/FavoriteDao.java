package com.example.foodplannerproject.data.favorite.data_source;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavoriteMeal(FavoriteMeal meal);

    @Delete
    Completable deleteFavoriteMeal(FavoriteMeal meal);

    @Query("SELECT * FROM favorite_meals_table")
    Flowable<List<FavoriteMeal>> getAllFavoriteMeals();

    @Query("SELECT * FROM favorite_meals_table WHERE id = :mealId LIMIT 1")
    Maybe<FavoriteMeal> getMealById(String mealId);
}

