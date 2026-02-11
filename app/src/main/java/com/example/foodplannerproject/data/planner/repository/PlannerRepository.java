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

    public PlannerRepository(PlannerLocalDataSource local,
                             PlannerRemoteDataSource remote) {
        this.local = local;
        this.remote = remote;
    }

    public Flowable<List<PlannerMeal>> getPlannerMeals(boolean isOnline,
                                                       String start,
                                                       String end) {
        if (isOnline) {
            return local.getAllMeals(); // show ALL days
        } else {
            return local.getMealsForWeek(start, end); // only 7 days
        }
    }

    public Completable addMeal(PlannerMeal meal, boolean isOnline) {

        if (isOnline) {
            return local.insertMeal(meal)
                    .andThen(
                            remote.insertMeal(meal)
                                    .onErrorComplete() // ignore firebase failure
                    );
        } else {
            return local.insertMeal(meal); // offline → Room only
        }
    }

}
