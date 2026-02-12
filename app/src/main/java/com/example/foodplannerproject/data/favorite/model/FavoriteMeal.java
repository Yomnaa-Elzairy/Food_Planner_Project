package com.example.foodplannerproject.data.favorite.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_meals_table")
public class FavoriteMeal {

    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private String imageUrl;

    // getters & setters

    public FavoriteMeal(@NonNull String id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}

