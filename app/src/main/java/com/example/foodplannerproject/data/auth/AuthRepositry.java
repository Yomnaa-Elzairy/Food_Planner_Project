package com.example.foodplannerproject.data.auth;

import com.example.foodplannerproject.data.auth.data_source.remote.AuthRemoteResponse;
import com.example.foodplannerproject.data.auth.data_source.remote.FireBaseAuthRemoteDataSource;

public class AuthRepositry {
    private FireBaseAuthRemoteDataSource fireBaseAuthRemoteDataSource;

    public AuthRepositry() {
        this.fireBaseAuthRemoteDataSource = new FireBaseAuthRemoteDataSource();
    }

    public void login(String email, String password, AuthRemoteResponse authRemoteResponse){
        fireBaseAuthRemoteDataSource.login(email,password,authRemoteResponse);

    }

    public void signup(String email,String password ,AuthRemoteResponse authRemoteResponse){
        fireBaseAuthRemoteDataSource.signup(email,password,authRemoteResponse);
    }

}
