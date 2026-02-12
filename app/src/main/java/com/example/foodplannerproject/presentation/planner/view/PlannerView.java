package com.example.foodplannerproject.presentation.planner.view;

import com.example.foodplannerproject.data.planner.model.PlannerMeal;

import java.util.List;

public interface PlannerView {
    void showPlanner(List<PlannerMeal> meals);
    void showMessage(String msg);
    void showError(String msg);
}
