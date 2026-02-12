package com.example.foodplannerproject.presentation.planner.model;

import com.example.foodplannerproject.data.planner.model.PlannerMeal;

import java.util.List;

public class DayGroup {
    public String date;
    public List<PlannerMeal> meals;

    public DayGroup(String date, List<PlannerMeal> meals) {
        this.date = date;
        this.meals = meals;
    }
}
