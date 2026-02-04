package com.example.foodplannerproject.presentation.signup.presenter;

import com.example.foodplannerproject.data.auth.AuthRepositry;
import com.example.foodplannerproject.data.auth.data_source.remote.AuthRemoteResponse;
import com.example.foodplannerproject.presentation.signup.view.SignUpFragment;
import com.example.foodplannerproject.presentation.signup.view.SignupView;

public class SignUpPresenterImp implements SignUpPresenter{
    private AuthRepositry authRepositry;
    private SignupView signupView;

    public SignUpPresenterImp(SignupView signupView) {
        this.authRepositry = new AuthRepositry();
        this.signupView = signupView;
    }

    @Override
    public void signUp(String email, String password) {
        authRepositry.signup(email, password, new AuthRemoteResponse() {
            @Override
            public void onSuccess() {
                signupView.OnSignupSuccess();
            }

            @Override
            public void onFailure(String errorMessage) {
                signupView.onSignupFailure(errorMessage);
            }
        });
    }
}
