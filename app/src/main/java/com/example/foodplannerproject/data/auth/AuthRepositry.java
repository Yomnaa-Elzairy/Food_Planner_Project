package com.example.foodplannerproject.data.auth;

import android.content.Context;

import com.example.foodplannerproject.data.auth.data_source.local.AuthLocalDataSource;
import com.example.foodplannerproject.data.auth.data_source.remote.AuthRemoteResponse;
import com.example.foodplannerproject.data.auth.data_source.remote.FireBaseAuthRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepositry {
    private FireBaseAuthRemoteDataSource fireBaseAuthRemoteDataSource;
    private AuthLocalDataSource authLocalDataSource;

    public AuthRepositry(Context context) {
        this.fireBaseAuthRemoteDataSource = new FireBaseAuthRemoteDataSource(context);
        this.authLocalDataSource = new AuthLocalDataSource(context);
    }

    public void login(String email, String password, AuthRemoteResponse authRemoteResponse){
        fireBaseAuthRemoteDataSource.login(email,password,authRemoteResponse);

    }

    public void signup(String email,String password ,AuthRemoteResponse authRemoteResponse){
        fireBaseAuthRemoteDataSource.signup(email,password,authRemoteResponse);
    }

    public void saveLoginState(boolean isLoggedin){
        authLocalDataSource.saveLoginState(isLoggedin);
    }

    public boolean isLoggedin(){
        return authLocalDataSource.isLoggedin();
    }

    public void logout(){
        fireBaseAuthRemoteDataSource.signout();
         authLocalDataSource.clear();
    }
}
