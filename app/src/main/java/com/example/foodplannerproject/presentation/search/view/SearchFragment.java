package com.example.foodplannerproject.presentation.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.model.Area;
import com.example.foodplannerproject.data.search.model.FilterMeal;
import com.example.foodplannerproject.data.search.model.Ingredient;
import com.example.foodplannerproject.presentation.search.presenter.SearchPresenter;
import com.example.foodplannerproject.presentation.search.presenter.SearchPresenterImp;
import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class SearchFragment extends Fragment implements SearchView,
        FilterAdapter.OnFilterClickListener,OnMealClickListener {

    private SearchPresenter presenter;
    private RecyclerView recyclerView;
    private FilterAdapter filterAdapter;
    private MealGridAdapter mealAdapter;

    private Chip chipArea, chipIngredient, chipCategory;

    @Override
    public void onMealClick(String id) {
        NavController navController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        Bundle bundle = new Bundle();
        bundle.putString("mealId", id);

        navController.navigate(R.id.mealDetails, bundle);
    }

    private enum FilterType { AREA, INGREDIENT, CATEGORY }
    private FilterType currentFilterType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        presenter = new SearchPresenterImp(this);

        recyclerView = view.findViewById(R.id.searchRecyclerView);
        chipArea = view.findViewById(R.id.chipArea);
        chipIngredient = view.findViewById(R.id.chipIngredient);
        chipCategory = view.findViewById(R.id.chipCategory);

        filterAdapter = new FilterAdapter(this);
        mealAdapter = new MealGridAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(filterAdapter);

        chipArea.setOnClickListener(v -> {
            currentFilterType = FilterType.AREA;
            presenter.getAreas();
        });

        chipIngredient.setOnClickListener(v -> {
            currentFilterType = FilterType.INGREDIENT;
            presenter.getIngredients();
        });

        chipCategory.setOnClickListener(v -> {
            currentFilterType = FilterType.CATEGORY;
            presenter.getCategories();
        });

        return view;
    }

    @Override
    public void onFilterClick(String value) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(mealAdapter);

        if (currentFilterType == FilterType.AREA)
            presenter.filterByArea(value);
        else if (currentFilterType == FilterType.INGREDIENT)
            presenter.filterByIngredient(value);
        else
            presenter.filterByCategory(value);
    }

    @Override
    public void showAreas(List<String> areas) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(filterAdapter);
        filterAdapter.setList(areas);
    }

    @Override
    public void showIngredients(List<String> ingredients) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(filterAdapter);
        filterAdapter.setList(ingredients);
    }

    @Override
    public void showCategories(List<String> categories) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(filterAdapter);
        filterAdapter.setList(categories);
        Log.d("SEARCH", "Categories size = " + categories.size());
    }

    @Override
    public void showFilteredMeals(List<FilterMeal> meals) {
        mealAdapter.setMeals(meals);
    }

    @Override
    public void showSearchedMeals(List<Meal> meals) {
        mealAdapter.setMeals(meals);
    }

    @Override
    public void showError(String message) {
        // Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}