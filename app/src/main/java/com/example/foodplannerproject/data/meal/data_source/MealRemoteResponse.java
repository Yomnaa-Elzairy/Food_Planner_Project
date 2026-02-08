package com.example.foodplannerproject.data.meal.data_source;

public interface MealRemoteResponse <T>{
    public void onSuccess(T data);
    public void onFailure(String msg);
    public void onNoInternet();
}
