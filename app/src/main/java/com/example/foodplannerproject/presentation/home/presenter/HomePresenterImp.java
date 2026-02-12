package com.example.foodplannerproject.presentation.home.presenter;

import com.example.foodplannerproject.data.meal.data_source.MealRemoteResponse;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.meal.repository.MealRepository;
import com.example.foodplannerproject.presentation.home.view.HomeView;

public class HomePresenterImp implements HomePresenter{
    private MealRepository mealRepository;
    private HomeView homeView;

    public HomePresenterImp(HomeView homeView) {
        this.homeView =homeView;
        this.mealRepository = new MealRepository();
        }


    @Override
    public void getRandomMeal() {
        mealRepository.getRandomMeal(new MealRemoteResponse<Meal>() {
            @Override
            public void onSuccess(Meal data) {
                homeView.hideLoading();
                homeView.showRandomMeal(data);
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
