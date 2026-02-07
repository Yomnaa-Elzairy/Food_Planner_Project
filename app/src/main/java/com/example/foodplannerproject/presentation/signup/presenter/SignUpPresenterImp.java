package com.example.foodplannerproject.presentation.signup.presenter;

import android.content.Context;

import com.example.foodplannerproject.data.auth.AuthRepositry;
import com.example.foodplannerproject.data.auth.data_source.remote.AuthRemoteResponse;
import com.example.foodplannerproject.presentation.signup.view.SignUpFragment;
import com.example.foodplannerproject.presentation.signup.view.SignupView;

public class SignUpPresenterImp implements SignUpPresenter{
    private AuthRepositry authRepositry;
    private SignupView signupView;

    public SignUpPresenterImp(SignupView signupView, Context context) {
        this.authRepositry = new AuthRepositry(context);
        this.signupView = signupView;
    }

    @Override
    public void signUp(String email, String password) {
        authRepositry.signup(email, password, new AuthRemoteResponse() {
            @Override
            public void onSuccess() {
                authRepositry.saveLoginState(true); // user is now logged in
                signupView.hideLoading();
                signupView.onSignUpSuccess();
            }

            @Override
            public void onFailure(String errorMessage) {
                signupView.hideLoading();
                signupView.onSignUpFailure(errorMessage);
            }

            @Override
            public void onNoInternet() {
                signupView.hideLoading();
                signupView.onNoInternet();
            }
        });
    }

    @Override
    public void signout() {
        authRepositry.logout();
    }

}
