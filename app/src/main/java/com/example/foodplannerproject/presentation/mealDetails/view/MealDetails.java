package com.example.foodplannerproject.presentation.mealDetails.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Locale;

public class MealDetails extends Fragment implements MealDetailsView {

    private IngredientAdapter ingredientAdapter;
    private StepsAdapter stepsAdapter;
    private RecyclerView rvIngredients, rvSteps;
    private MealDetailsPresenter presenter;
    private TextView mealName;
    private Meal currentMeal;
    private FloatingActionButton addToPlanner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        rvIngredients = view.findViewById(R.id.rvIngredients);
        rvSteps = view.findViewById(R.id.rvSteps);
        mealName =view.findViewById(R.id.mealName);
        addToPlanner = view.findViewById(R.id.fabPlanner);

        ingredientAdapter = new IngredientAdapter();
        stepsAdapter = new StepsAdapter();

        rvIngredients.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        rvIngredients.setAdapter(ingredientAdapter);

        rvSteps.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSteps.setAdapter(stepsAdapter);

        presenter = new MealDetailsPresenterImp(this,requireContext());

        // ✅ SafeArgs: get mealId
        MealDetailsArgs args = MealDetailsArgs.fromBundle(getArguments());
        String mealId = args.getMealId();

        presenter.getMealDetailsById(mealId);
        addToPlanner.setOnClickListener(v->
                showDatePicker());

        return view;
    }

    @Override
    public void showLoading() {
        // progress bar if you want
    }

    @Override
    public void showMessage(String msg) {
        if(getContext()!=null)
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        if(getContext()!=null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void hideLoading() {
        // hide progress bar
    }


    @Override
    public void showNoInternet() {
        // Toast
    }

    @Override
    public void getMealById(Meal meal) {
        currentMeal = meal;
        mealName.setText(meal.getName());
        ingredientAdapter.setList(meal.getIngredientList());
        stepsAdapter.setList(meal.getInstructionSteps());
    }
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String date = String.format(
                            Locale.US,
                            "%04d-%02d-%02d",
                            selectedYear,
                            selectedMonth + 1,
                            selectedDay
                    );

                    presenter.addToPlanner(currentMeal, date); // 👈 send to presenter
                },
                year, month, day
        );

        dialog.show();
    }



}
