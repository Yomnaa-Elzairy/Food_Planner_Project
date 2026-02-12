package com.example.foodplannerproject.data.planner.data_source;

import android.content.Context;

import com.example.foodplannerproject.data.database.AppDatabase;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerLocalDataSource {

    private PlannerDao dao;

    public PlannerLocalDataSource(Context context) {
        this.dao = AppDatabase.getInstance(context).PlannerDao();
    }

    public Completable insertMeal(PlannerMeal meal) {
        return dao.insertMeal(meal);
    }

    public Flowable<List<PlannerMeal>> getAllMeals() {
        return dao.getAllMeals();
    }

    public Flowable<List<PlannerMeal>> getMealsForWeek(String start, String end) {
        return dao.getMealsForWeek(start, end);
    }
}
