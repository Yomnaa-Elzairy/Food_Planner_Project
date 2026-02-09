package com.example.foodplannerproject.data.search.data_source;

import com.example.foodplannerproject.data.network.ApiService;
import com.example.foodplannerproject.data.network.RetrofitClient;
import com.example.foodplannerproject.data.search.model.SearchItem;
import com.example.foodplannerproject.data.search.model.SearchMeal;
import com.example.foodplannerproject.data.search.model.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRemoteDataSource {
        private ApiService apiService;

    public SearchRemoteDataSource() {
        apiService= RetrofitClient.getInstance()
                .create(ApiService.class);
    }

    public void searchByFilter(String filterType,String userInput, SearchRemoteResponse<SearchMeal> callback){
       Call<SearchResponse> call;
       if(filterType.equals("i"))
           call = apiService.searchByFilter(userInput,null,null);
       else if(filterType.equals("c"))
           call = apiService.searchByFilter(null,userInput,null);
       else
           call = apiService.searchByFilter(null,null,userInput);

       call.enqueue(new Callback<SearchResponse>() {
           @Override
           public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
               if (response.isSuccessful() && response.body() != null) {
                   callback.onSuccess(response.body().getMeals());
               }
           }

           @Override
           public void onFailure(Call<SearchResponse> call, Throwable t) {
               callback.onFailure(t.getMessage());
           }
       });
    }

}
