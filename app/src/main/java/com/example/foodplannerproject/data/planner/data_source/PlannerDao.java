package com.example.foodplannerproject.data.planner.data_source;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplannerproject.data.planner.model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlannerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(PlannerMeal meal);

    @Query("SELECT * FROM planner_meals ORDER BY date")
    Flowable<List<PlannerMeal>> getAllMeals();

    @Query("SELECT * FROM planner_meals WHERE date BETWEEN :start AND :end ORDER BY date")
    Flowable<List<PlannerMeal>> getMealsForWeek(String start, String end);

    @Delete
    Completable deleteMeal(PlannerMeal meal);
}

