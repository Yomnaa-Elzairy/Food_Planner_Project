package com.example.foodplannerproject.data.planner.repository;

import android.content.Context;

import com.example.foodplannerproject.data.planner.data_source.PlannerLocalDataSource;
import com.example.foodplannerproject.data.planner.data_source.PlannerRemoteDataSource;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;



public class PlannerRepository {

    private PlannerLocalDataSource local;
    private PlannerRemoteDataSource remote;

    public PlannerRepository(Context context) {
        this.local = new PlannerLocalDataSource(context);
        this.remote = new PlannerRemoteDataSource();
    }

    // SAME data online/offline → only UI decides filtering
    public Flowable<List<PlannerMeal>> getPlannerMeals(boolean isOnline, String start, String end) {

        return isOnline? local.getAllMeals() : local.getMealsForWeek(start, end);

    }

    public Completable addMeal(PlannerMeal meal, boolean isOnline) {
        if (isOnline) {
            return local.insertMeal(meal)
                    .andThen(
                            remote.insertMeal(meal)
                                    .onErrorComplete() // firebase may fail
                    );
        } else {
            return local.insertMeal(meal);
        }
    }
}
