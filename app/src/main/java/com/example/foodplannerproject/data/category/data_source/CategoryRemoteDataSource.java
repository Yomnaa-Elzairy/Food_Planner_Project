package com.example.foodplannerproject.data.category.data_source;

import com.example.foodplannerproject.data.category.model.Category;
import com.example.foodplannerproject.data.category.model.CategoryResponse;
import com.example.foodplannerproject.data.network.ApiService;
import com.example.foodplannerproject.data.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRemoteDataSource {
    private ApiService apiService;

    public CategoryRemoteDataSource() {
        apiService = RetrofitClient.getInstance()
                .create(ApiService.class);
    }

    public void getAllCategories(CategoryRemoteResponse<Category> callback){
        apiService.getAllCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                   List<Category> categories = response.body().getCategories();
                   callback.onSuccess(categories);
                }
                else callback.onFailure("No category found");

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                 callback.onFailure(t.getMessage());
            }
        });

    }
}
