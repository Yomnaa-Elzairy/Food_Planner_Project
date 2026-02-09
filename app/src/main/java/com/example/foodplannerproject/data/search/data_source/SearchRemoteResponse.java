package com.example.foodplannerproject.data.search.data_source;

import java.util.List;

public interface SearchRemoteResponse<T> {
    public void onSuccess(List<T> data);
    public void onFailure(String msg);
    public void onNoInternet();
}
