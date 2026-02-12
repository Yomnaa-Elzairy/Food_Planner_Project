package com.example.foodplannerproject.presentation.mealDetails.presenter;

import android.content.Context;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;
import com.example.foodplannerproject.data.favorite.repository.FavoriteRepository;
import com.example.foodplannerproject.data.meal.data_source.MealRemoteResponse;
import com.example.foodplannerproject.data.meal.model.Ingredients;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.meal.model.MealResponse;
import com.example.foodplannerproject.data.meal.repository.MealRepository;
import com.example.foodplannerproject.data.network.CheckNetwork;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;
import com.example.foodplannerproject.data.planner.repository.PlannerRepository;
import com.example.foodplannerproject.presentation.mealDetails.view.MealDetailsView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public  class MealDetailsPresenterImp implements MealDetailsPresenter{
    private MealRepository mealRepository;
    private MealDetailsView mealDetailsView;
    private PlannerRepository plannerRepository;
    private FavoriteRepository favoriteRepository;
    private Context context;



    public MealDetailsPresenterImp(MealDetailsView mealDetailsView, Context context) {
        this.mealRepository = new MealRepository();
        this.plannerRepository = new PlannerRepository(context);
        this.favoriteRepository =  new FavoriteRepository(context);
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
                mealDetailsView.showMealVideo(getYouTubeId(data.getVideo()));
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

    @Override
    public void addToFavorite(FavoriteMeal meal) {
        favoriteRepository.insertFavoriteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> mealDetailsView.onMealAddedToFavorite(),
                        throwable -> mealDetailsView.showError(throwable.getMessage())
                );
    }

    @Override
    public void checkIfFavorite(String mealId) {
        favoriteRepository.getMealById(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meal -> mealDetailsView.showFavoriteState(true),   // exists
                        throwable -> mealDetailsView.showError(throwable.getMessage()),
                        () -> mealDetailsView.showFavoriteState(false)     // not found
                );
    }

    @Override
    public void toggleFavorite(FavoriteMeal meal) {
        favoriteRepository.getMealById(meal.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        existingMeal -> {
                            // already favorite → remove
                            favoriteRepository.deleteFavoriteMeal(existingMeal)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(() -> mealDetailsView.onMealRemovedFromFavorite());
                        },
                        throwable -> mealDetailsView.showError(throwable.getMessage()),
                        () -> {
                            // not favorite → insert
                            favoriteRepository.insertFavoriteMeal(meal)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(() -> mealDetailsView.onMealAddedToFavorite());
                        }
                );

    }

    private String getYouTubeId(String url) {
        Pattern pattern = Pattern.compile("v=([a-zA-Z0-9_-]+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
