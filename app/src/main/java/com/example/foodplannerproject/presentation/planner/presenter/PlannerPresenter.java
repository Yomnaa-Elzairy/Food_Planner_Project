package com.example.foodplannerproject.presentation.planner.presenter;

import com.example.foodplannerproject.data.meal.model.Meal;

public interface PlannerPresenter {
    void loadPlanner(boolean isOnline);
    void addMeal(Meal meal, String date);
    void clear();
}
