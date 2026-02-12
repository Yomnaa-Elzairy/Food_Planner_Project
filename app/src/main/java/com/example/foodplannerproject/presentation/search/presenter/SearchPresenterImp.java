package com.example.foodplannerproject.presentation.search.presenter;

import com.example.foodplannerproject.data.search.data_source.SearchRemoteResponse;
import com.example.foodplannerproject.data.search.repository.SearchRepository;
import com.example.foodplannerproject.presentation.search.view.SearchView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {

    private SearchView view;
    private SearchRepository repository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SearchPresenterImp(SearchView view) {
        this.view = view;
        repository = new SearchRepository();
    }

    @Override
    public void getAreas() {
        view.showLoading();
        compositeDisposable.add(
                repository.getAreas()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                areas -> {
                                    view.hideLoading();
                                    view.showAreas(areas);
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void getIngredients() {
        view.showLoading();
        compositeDisposable.add(
                repository.getIngredients()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                ingredients -> {
                                    view.hideLoading();
                                    view.showIngredients(ingredients);
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void getCategories() {
        view.showLoading();
        compositeDisposable.add(
                repository.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                categories -> {
                                    view.hideLoading();
                                    view.showCategories(categories);
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }


    @Override
    public void filterByCategory(String category) {
        view.showLoading();
        compositeDisposable.add(
                repository.filterByCategory(category)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    view.hideLoading();
                                    view.showFilteredMeals(meals);
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void filterByArea(String area) {
        view.showLoading();
        compositeDisposable.add(
                repository.filterByArea(area)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    view.hideLoading();
                                    view.showFilteredMeals(meals);
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void filterByIngredient(String ingredient) {
        view.showLoading();
        compositeDisposable.add(
                repository.filterByIngredient(ingredient)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    view.hideLoading();
                                    view.showFilteredMeals(meals);
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void searchByName(String name) {
        view.showLoading();
        compositeDisposable.add(
                repository.searchByName(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    view.hideLoading();
                                    view.showSearchedMeals(meals);
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void detachView() {
        compositeDisposable.clear();
        view = null;
    }
}