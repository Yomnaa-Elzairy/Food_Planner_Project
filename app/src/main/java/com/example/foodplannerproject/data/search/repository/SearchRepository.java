package com.example.foodplannerproject.data.search.repository;

import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.data_source.SearchRemoteDataSource;
import com.example.foodplannerproject.data.search.data_source.SearchRemoteResponse;
import com.example.foodplannerproject.data.search.model.SearchMeal;

public class SearchRepository {
    private SearchRemoteDataSource searchRemoteDataSource;

    public SearchRepository() {
        this.searchRemoteDataSource = new SearchRemoteDataSource();
    }

    public void searchByFilter(String filterType, String userInput, SearchRemoteResponse<SearchMeal> callback){
        searchRemoteDataSource.searchByFilter(filterType,userInput,callback);
    }
}
