package com.example.foodplannerproject.presentation.home.presenter;

import com.example.foodplannerproject.data.category.data_source.CategoryRemoteResponse;
import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.repository.CategoryRepository;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.presentation.home.view.HomeView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryPresenterImp implements CategoryPresenter{
    private CategoryRepository categoryRepository;
    private HomeView homeView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CategoryPresenterImp(HomeView homeView) {
        this.categoryRepository = new CategoryRepository();
        this.homeView = homeView;
    }

    @Override
    public void getAllCategories() {
        homeView.showLoading();
        compositeDisposable.add(
                categoryRepository.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                categories -> {
                                    homeView.hideLoading();
                                    homeView.showAllCategories(categories);
                                },
                                throwable -> {
                                    homeView.hideLoading();
                                    homeView.showError(throwable.getMessage());
                                }
                        )
        );
    }

}
