package com.example.foodplannerproject.presentation.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.data.search.model.SearchMeal;
import com.example.foodplannerproject.presentation.search.presenter.SearchPresenter;
import com.example.foodplannerproject.presentation.search.presenter.SearchPresenterImp;
import com.example.foodplannerproject.presentation.search.view.SearchFragmentDirections;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView ,OnMealClickListener{

    RecyclerView recyclerView;
    SearchAdapter adapter;
    SearchPresenter presenter;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerRecipes);
        progressBar = view.findViewById(R.id.progressBar); // add in xml if not exists

        adapter = new SearchAdapter(this::onMealClick);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        presenter = new SearchPresenterImp(this);

        // 🔹 test call (later replace with real filter input)
        presenter.searchByFilter("a", "canadian");
        // c = category, i = ingredient, a = area (MealDB API)

        return view;
    }

    // ================= MVP CALLBACKS =================

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void searchByFilter(List<SearchMeal> meals) {
        adapter.setMeals(meals);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoInternet() {
        Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(String id) {
        SearchFragmentDirections.ActionSearchFragmentToMealDetails action =
                SearchFragmentDirections.actionSearchFragmentToMealDetails(id);

        NavHostFragment.findNavController(this).navigate(action);
    }
}
