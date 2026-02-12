package com.example.foodplannerproject.presentation.login.view;

public interface LoginView {
    void showLoading();
    void hideLoading();
    void onLoginSuccess();
    void onLoginFailure(String message);
    void onNoInternet();
}
