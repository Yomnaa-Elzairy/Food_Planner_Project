package com.example.foodplannerproject.presentation.search.presenter;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.data_source.SearchRemoteResponse;
import com.example.foodplannerproject.data.search.model.SearchMeal;
import com.example.foodplannerproject.data.search.repository.SearchRepository;
import com.example.foodplannerproject.presentation.home.view.HomeView;
import com.example.foodplannerproject.presentation.search.view.SearchView;

import java.util.List;

public class SearchPresenterImp implements SearchPresenter{
   private SearchRepository searchRepository;
   private SearchView searchView;

    public SearchPresenterImp(SearchView searchView) {
        this.searchRepository =new SearchRepository();
        this.searchView = searchView;
    }

    @Override
    public void searchByFilter(String filterType, String userInput) {
        searchRepository.searchByFilter(filterType, userInput, new SearchRemoteResponse<SearchMeal>() {
            @Override
            public void onSuccess(List<SearchMeal> data) {
                searchView.hideLoading();
                searchView.searchByFilter(data);
            }

            @Override
            public void onFailure(String msg) {
                searchView.hideLoading();
                searchView.showError(msg);
            }

            @Override
            public void onNoInternet() {
                searchView.hideLoading();
                searchView.showNoInternet();
            }
        });
    }
}
