package com.example.foodplannerproject.presentation.mealDetails.presenter;

import com.example.foodplannerproject.data.meal.data_source.MealRemoteResponse;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.meal.repository.MealRepository;
import com.example.foodplannerproject.presentation.mealDetails.view.MealDetailsView;

public class MealDetailsPresenterImp implements MealDetailsPresenter{
    private MealRepository mealRepository;
    private MealDetailsView mealDetailsView;

    public MealDetailsPresenterImp(MealDetailsView mealDetailsView) {
        this.mealRepository = new MealRepository();
        this.mealDetailsView = mealDetailsView;
    }

    @Override
    public void getMealDetailsById(String id) {
        mealRepository.getMealById(id, new MealRemoteResponse<Meal>() {
            @Override
            public void onSuccess(Meal data) {
                mealDetailsView.hideLoading();
                mealDetailsView.getMealById(data);
            }

            @Override
            public void onFailure(String msg) {
                mealDetailsView.hideLoading();
                mealDetailsView.showError(msg);
            }

            @Override
            public void onNoInternet() {
                mealDetailsView.hideLoading();
                mealDetailsView.showError("No Internet connection");
            }
        });
    }
}
