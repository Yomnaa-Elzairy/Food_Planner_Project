package com.example.foodplannerproject.data.planner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "planner_meals")
public class PlannerMeal {

    @PrimaryKey
    @NonNull
    private String key; // idMeal_date

    private String idMeal;
    private String name;
    private String image;
    private String date; // yyyy-MM-dd

    public PlannerMeal(@NonNull String key,
                       String idMeal,
                       String name,
                       String image,
                       String date) {
        this.key = key;
        this.idMeal = idMeal;
        this.name = name;
        this.image = image;
        this.date = date;
    }

    // helper constructor (not used by Room)
    @Ignore
    public PlannerMeal(String idMeal, String name, String image, String date) {
        this.key = idMeal + "_" + date;
        this.idMeal = idMeal;
        this.name = name;
        this.image = image;
        this.date = date;
    }

    public String getKey() { return key; }
    public String getIdMeal() { return idMeal; }
    public String getName() { return name; }
    public String getImage() { return image; }
    public String getDate() { return date; }
}
