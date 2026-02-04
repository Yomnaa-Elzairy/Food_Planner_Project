package com.example.foodplannerproject.presentation.login.presenter;

import com.example.foodplannerproject.data.auth.data_source.remote.AuthRemoteResponse;

public interface LoginPresenter {

    public void login(String email, String password);
}
