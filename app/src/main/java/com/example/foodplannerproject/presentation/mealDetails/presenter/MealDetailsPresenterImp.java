package com.example.foodplannerproject.presentation.mealDetails.presenter;

import android.content.Context;

import com.example.foodplannerproject.data.meal.data_source.MealRemoteResponse;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.meal.repository.MealRepository;
import com.example.foodplannerproject.data.network.CheckNetwork;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;
import com.example.foodplannerproject.data.planner.repository.PlannerRepository;
import com.example.foodplannerproject.presentation.mealDetails.view.MealDetailsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter{
    private MealRepository mealRepository;
    private MealDetailsView mealDetailsView;
    private PlannerRepository plannerRepository;
    private Context context;


    public MealDetailsPresenterImp(MealDetailsView mealDetailsView, Context context) {
        this.mealRepository = new MealRepository();
        this.plannerRepository = new PlannerRepository(context);
        this.mealDetailsView = mealDetailsView;
        this.context =context;
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

    @Override
    public void addToPlanner(Meal meal, String date) {

        plannerRepository.addMeal(
                new PlannerMeal(meal.getId(), meal.getName(), meal.getImage(), date),
                CheckNetwork.isConnected(context)
        ).subscribe(
                () -> mealDetailsView.showMessage("Added to planner"),
                e -> mealDetailsView.showError("Failed to add")
        );
    }

}
