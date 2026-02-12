package com.example.foodplannerproject.presentation.planner.presenter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.util.Log;

import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.network.CheckNetwork;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;
import com.example.foodplannerproject.data.planner.repository.PlannerRepository;
import com.example.foodplannerproject.presentation.planner.model.DateUtils;
import com.example.foodplannerproject.presentation.planner.view.PlannerView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerPresenterImp implements PlannerPresenter {

    private PlannerView view;
    private PlannerRepository repo;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Context context;

    public PlannerPresenterImp(PlannerView view, Context context) {
        this.view = view;
        this.repo = new PlannerRepository(context);
        this.context = context;
    }

    @Override
    public void loadPlanner(boolean isOnline) {
        String start = DateUtils.getStartOfWeek();
        String end = DateUtils.getEndOfWeek();

        Log.d("PlannerDebug", "start=" + start + " end=" + end);

        disposable.add(
                repo.getPlannerMeals(isOnline,start, end)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    Log.d("PlannerDebug", "Meals size=" + meals.size());
                                    view.showPlanner(meals);
                                },
                                e -> view.showError(e.getMessage())
                        )
        );
    }

    @Override
    public void addMeal(Meal meal, String date) {
        PlannerMeal plannerMeal = new PlannerMeal(
                meal.getId(),
                meal.getName(),
                meal.getImage(),
                date
        );

        boolean isOnline = CheckNetwork.isConnected(context);

        Log.d("PlannerDebug", "Saving meal date=" + date);

        disposable.add(
                repo.addMeal(plannerMeal, isOnline)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> view.showMessage("Added to planner"),
                                e -> view.showError("Failed to add")
                        )
        );
    }

    @Override
    public void clear() {
        disposable.clear();
    }
}