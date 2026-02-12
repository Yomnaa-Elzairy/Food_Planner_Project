package com.example.foodplannerproject.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplannerproject.data.planner.data_source.PlannerDao;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;

@Database(entities = {PlannerMeal.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract PlannerDao PlannerDao();
    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null ){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "food_planner_db"
            ).build();
        }
        return instance;
    }

}
