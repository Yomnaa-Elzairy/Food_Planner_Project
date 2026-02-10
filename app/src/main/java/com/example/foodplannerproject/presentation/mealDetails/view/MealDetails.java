package com.example.foodplannerproject.presentation.mealDetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.presentation.meal.view.IngredientAdapter;
import com.example.foodplannerproject.presentation.mealDetails.presenter.MealDetailsPresenterImp;
import com.example.foodplannerproject.presentation.mealDetails.view.StepsAdapter;
import com.example.foodplannerproject.presentation.mealDetails.presenter.MealDetailsPresenter;

public class MealDetails extends Fragment implements MealDetailsView {

    private IngredientAdapter ingredientAdapter;
    private StepsAdapter stepsAdapter;
    private RecyclerView rvIngredients, rvSteps;
    private MealDetailsPresenter presenter;
    private TextView mealName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        rvIngredients = view.findViewById(R.id.rvIngredients);
        rvSteps = view.findViewById(R.id.rvSteps);
        mealName =view.findViewById(R.id.mealName);

        ingredientAdapter = new IngredientAdapter();
        stepsAdapter = new StepsAdapter();

        rvIngredients.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        rvIngredients.setAdapter(ingredientAdapter);

        rvSteps.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSteps.setAdapter(stepsAdapter);

        presenter = new MealDetailsPresenterImp(this);

        // ✅ SafeArgs: get mealId
        MealDetailsArgs args = MealDetailsArgs.fromBundle(getArguments());
        String mealId = args.getMealId();

        presenter.getMealDetailsById(mealId);

        return view;
    }

    @Override
    public void showLoading() {
        // progress bar if you want
    }

    @Override
    public void hideLoading() {
        // hide progress bar
    }

    @Override
    public void showError(String message) {
        // Toast or Snackbar
    }

    @Override
    public void showNoInternet() {
        // Toast
    }

    @Override
    public void getMealById(Meal meal) {
        mealName.setText(meal.getName());
        ingredientAdapter.setList(meal.getIngredientList());
        stepsAdapter.setList(meal.getInstructionSteps());
    }
}
