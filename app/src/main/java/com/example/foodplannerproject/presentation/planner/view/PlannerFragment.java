package com.example.foodplannerproject.presentation.planner.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.database.AppDatabase;
import com.example.foodplannerproject.data.network.CheckNetwork;
import com.example.foodplannerproject.data.planner.data_source.PlannerDao;
import com.example.foodplannerproject.data.planner.data_source.PlannerLocalDataSource;
import com.example.foodplannerproject.data.planner.data_source.PlannerRemoteDataSource;
import com.example.foodplannerproject.data.planner.model.PlannerMeal;
import com.example.foodplannerproject.data.planner.repository.PlannerRepository;
import com.example.foodplannerproject.presentation.planner.model.DayGroup;
import com.example.foodplannerproject.presentation.planner.presenter.PlannerPresenter;
import com.example.foodplannerproject.presentation.planner.presenter.PlannerPresenterImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class PlannerFragment extends Fragment implements PlannerView {

    private PlannerPresenter presenter;
    private DayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_planner, container, false);

        RecyclerView rv = view.findViewById(R.id.rvMeals);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DayAdapter();
        rv.setAdapter(adapter);

        PlannerDao dao = AppDatabase.getInstance(requireContext()).PlannerDao();

        PlannerRepository repo = new PlannerRepository(
                new PlannerLocalDataSource(dao),
                new PlannerRemoteDataSource()
        );

        presenter = new PlannerPresenterImpl(this, repo,requireContext());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isOnline = CheckNetwork.isConnected(requireContext());
        presenter.loadPlanner(isOnline);
    }

    @Override
    public void showPlanner(List<PlannerMeal> meals) {
        adapter.setData(groupByDate(meals));
    }

    private List<DayGroup> groupByDate(List<PlannerMeal> meals) {
        Map<String, List<PlannerMeal>> map = new LinkedHashMap<>();

        for (PlannerMeal meal : meals) {
            if (!map.containsKey(meal.getDate())) {
                map.put(meal.getDate(), new ArrayList<>());
            }
            map.get(meal.getDate()).add(meal);
        }

        List<DayGroup> list = new ArrayList<>();
        for (String date : map.keySet()) {
            list.add(new DayGroup(date, map.get(date)));
        }
        return list;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
