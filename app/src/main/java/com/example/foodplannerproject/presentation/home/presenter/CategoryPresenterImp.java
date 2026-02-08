package com.example.foodplannerproject.presentation.home.presenter;

import com.example.foodplannerproject.data.category.data_source.CategoryRemoteResponse;
import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.repository.CategoryRepository;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.presentation.home.view.HomeView;

import java.util.List;

public class CategoryPresenterImp implements CategoryPresenter{
    private CategoryRepository categoryRepository;
    private HomeView homeView;

    public CategoryPresenterImp(HomeView homeView) {
        this.categoryRepository = new CategoryRepository();
        this.homeView = homeView;
    }

    @Override
    public void getAllCategories() {
        categoryRepository.getAllCategories(new CategoryRemoteResponse<Category>() {
            @Override
            public void onSuccess(List<Category> data) {
                homeView.hideLoading();
                homeView.showAllCategories(data);
            }

            @Override
            public void onFailure(String msg) {
                homeView.hideLoading();
                homeView.showError(msg);
            }

            @Override
            public void onNoInternet() {
                homeView.hideLoading();
                homeView.showNoInternet();
            }
        });
    }
}
