package com.example.foodplannerproject.presentation.login.presenter;

import com.example.foodplannerproject.data.auth.AuthRepositry;
import com.example.foodplannerproject.data.auth.data_source.remote.AuthRemoteResponse;
import com.example.foodplannerproject.presentation.login.view.LoginView;

public class LoginPresenterImp implements LoginPresenter{
    private AuthRepositry authRepositry;
    private LoginView loginView;
    public LoginPresenterImp(LoginView loginView) {
        this.authRepositry = new AuthRepositry();
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password) {
        authRepositry.login(email, password, new AuthRemoteResponse() {
            @Override
            public void onSuccess() {
                loginView.OnLoginSuccess();
            }

            @Override
            public void onFailure(String errorMessage) {
                loginView.onLoginFailure(errorMessage);
            }
        });
    }
}
