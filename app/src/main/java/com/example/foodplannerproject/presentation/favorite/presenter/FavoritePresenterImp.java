package com.example.foodplannerproject.presentation.favorite.presenter;

import android.content.Context;

import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;
import com.example.foodplannerproject.data.favorite.repository.FavoriteRepository;
import com.example.foodplannerproject.presentation.favorite.view.FavoriteView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImp implements FavoritePresenter {

    private FavoriteView view;
    private FavoriteRepository repository;

    public FavoritePresenterImp(FavoriteView view, Context context) {
        this.view = view;
        this.repository = new FavoriteRepository(context);
    }

    @Override
    public void getAllFavorites() {
        repository.getAllFavoriteMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.showFavorites(meals),
                        throwable -> view.showError(throwable.getMessage())
                );
    }

    @Override
    public void deleteFavorite(FavoriteMeal meal) {
        repository.deleteFavoriteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
