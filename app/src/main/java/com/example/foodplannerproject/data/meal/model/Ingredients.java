package com.example.foodplannerproject.data.meal.model;

public class Ingredients {
    private String name;
    private String measure;
    public Ingredients(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public String getMeasures() {
        return measure;
    }
}
