package com.example.foodplannerproject.data.auth.data_source.remote;

public interface AuthRemoteResponse {
    public void onSuccess();
    public void onFailure(String errorMessage);
    void onNoInternet();
}
