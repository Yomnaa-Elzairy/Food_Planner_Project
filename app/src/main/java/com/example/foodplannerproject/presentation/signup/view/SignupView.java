package com.example.foodplannerproject.presentation.signup.view;

public interface SignupView {
    void showLoading();
    void hideLoading();
    void onSignUpSuccess();
    void onSignUpFailure(String message);
    void onNoInternet();
}
