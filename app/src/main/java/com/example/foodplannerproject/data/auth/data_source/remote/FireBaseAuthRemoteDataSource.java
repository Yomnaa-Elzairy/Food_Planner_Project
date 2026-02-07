package com.example.foodplannerproject.data.auth.data_source.remote;

import android.content.Context;

import com.example.foodplannerproject.data.network.CheckNetwork;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthRemoteDataSource {
    private FirebaseAuth firebaseAuth;
    private Context context;

    public FireBaseAuthRemoteDataSource(Context context) {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.context = context;
    }

    public void login(String email,String password,AuthRemoteResponse authRemoteResponse){
        if (!CheckNetwork.isConnected(context)) {
            authRemoteResponse.onNoInternet();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                authRemoteResponse.onSuccess();
                            } else {
                                String errorMessage;

                                if (task.getException() != null) {
                                    errorMessage = task.getException().getMessage();
                                } else {
                                    errorMessage = "Login failed. Please try again.";
                                }

                                authRemoteResponse.onFailure(errorMessage);
                            }
                        });
    }

    public void signup(String email,String password,AuthRemoteResponse authRemoteResponse){

        if (!CheckNetwork.isConnected(context)) {
            authRemoteResponse.onNoInternet();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        authRemoteResponse.onSuccess();
                    } else {
                        String errorMessage;

                        if (task.getException() != null) {
                            errorMessage = task.getException().getMessage();
                        } else {
                            errorMessage = "Signup failed. Please try again.";
                        }

                        authRemoteResponse.onFailure(errorMessage);
                    }
                });

    }
    public void signout(){
        firebaseAuth.signOut();
    }
}
