package com.example.foodplannerproject.presentation.favorite.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.favorite.model.FavoriteMeal;
import com.example.foodplannerproject.data.favorite.repository.FavoriteRepository;
import com.example.foodplannerproject.presentation.favorite.presenter.FavoritePresenter;
import com.example.foodplannerproject.presentation.favorite.presenter.FavoritePresenterImp;

import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteView {

    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private FavoritePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = view.findViewById(R.id.rvFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteAdapter(getContext());
        recyclerView.setAdapter(adapter);

        presenter = new FavoritePresenterImp(
                this,
                requireContext()
        );

        presenter.getAllFavorites();

        return view;
    }

    @Override
    public void showFavorites(List<FavoriteMeal> meals) {
        adapter.setList(meals);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
