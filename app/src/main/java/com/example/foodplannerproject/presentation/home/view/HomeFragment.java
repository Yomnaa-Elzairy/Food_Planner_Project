package com.example.foodplannerproject.presentation.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.meal.model.Meal;
import com.example.foodplannerproject.presentation.home.presenter.CategoryPresenter;
import com.example.foodplannerproject.presentation.home.presenter.CategoryPresenterImp;
import com.example.foodplannerproject.presentation.home.presenter.HomePresenter;
import com.example.foodplannerproject.presentation.home.presenter.HomePresenterImp;

import java.util.List;

public class HomeFragment extends Fragment implements HomeView {

    private HomePresenter homePresenter;
    private CategoryPresenter categoryPresenter;

    private ImageView imgHero;
    private TextView txtTitle;
    private ProgressBar progressBar;
    private CategoryAdapter categoryAdapter;
    private RecyclerView categoryRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imgHero = view.findViewById(R.id.imgHero);
        txtTitle = view.findViewById(R.id.txtTitle);
        categoryRecycler = view.findViewById(R.id.recyclerCategories);
        progressBar = new ProgressBar(requireContext());
        categoryAdapter = new CategoryAdapter();
        categoryRecycler.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        categoryRecycler.setAdapter(categoryAdapter);
//        categoryRecycler.setNestedScrollingEnabled(false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        homePresenter = new HomePresenterImp(this);
        categoryPresenter = new CategoryPresenterImp(this);
        homePresenter.getRandomMeal();
        categoryPresenter.getAllCategories();

        return view;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        imgHero.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        imgHero.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRandomMeal(Meal meal) {

        Glide.with(requireContext())
                .load(meal.getImage())
                .into(imgHero);
    }

    @Override
    public void showAllCategories(List<Category> categories) {
        Log.d("HomeFragment", "Categories size: " + categories.size());
        categoryAdapter.setCategories(categories);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoInternet() {
        Toast.makeText(requireContext(),
                "No internet connection",
                Toast.LENGTH_LONG).show();
    }
}
