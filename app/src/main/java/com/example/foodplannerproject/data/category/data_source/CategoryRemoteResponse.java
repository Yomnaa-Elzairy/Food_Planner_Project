package com.example.foodplannerproject.data.category.data_source;

import java.util.List;

public interface CategoryRemoteResponse<T> {
    public void onSuccess(List<T> data);
    public void onFailure(String msg);

    public void onNoInternet();
}
