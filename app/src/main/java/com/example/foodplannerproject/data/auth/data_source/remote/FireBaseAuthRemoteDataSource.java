package com.example.foodplannerproject.data.auth.data_source.remote;

import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthRemoteDataSource {
    private FirebaseAuth firebaseAuth;

    public FireBaseAuthRemoteDataSource() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(String email,String password,AuthRemoteResponse authRemoteResponse){
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
}
