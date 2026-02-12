package com.example.foodplannerproject.presentation.login.presenter;

import android.content.Context;

import com.example.foodplannerproject.data.auth.AuthRepositry;
import com.example.foodplannerproject.data.auth.data_source.remote.AuthRemoteResponse;
import com.example.foodplannerproject.presentation.login.view.LoginView;

public class LoginPresenterImp implements LoginPresenter{
    private AuthRepositry authRepositry;
    private LoginView loginView;
    public LoginPresenterImp(LoginView loginView, Context context) {
        this.authRepositry = new AuthRepositry(context);
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password) {
        authRepositry.login(email, password, new AuthRemoteResponse() {
            @Override
            public void onSuccess() {
                authRepositry.saveLoginState(true);
                loginView.hideLoading();
                loginView.onLoginSuccess();
            }

            @Override
            public void onFailure(String errorMessage) {
                loginView.hideLoading();
                loginView.onLoginFailure(errorMessage);
            }

            @Override
            public void onNoInternet() {
                loginView.hideLoading();
                loginView.onNoInternet();

            }
        });
    }

    @Override
    public boolean isUserLoggedin() {
        return authRepositry.isLoggedin();
    }


    @Override
    public void signout() {
        authRepositry.logout();
    }
}
