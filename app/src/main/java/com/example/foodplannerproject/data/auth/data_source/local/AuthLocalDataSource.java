package com.example.foodplannerproject.data.auth.data_source.local;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthLocalDataSource {
    private static final String prefName = "auth_prefs";
    private static final String keyLogin = "isLoggedIn";
    private SharedPreferences sharedPreferences;
    public AuthLocalDataSource(Context context) {
        sharedPreferences =context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
    }

    public void saveLoginState(boolean isLoggedin){
        sharedPreferences.edit().putBoolean(keyLogin,isLoggedin).apply();
    }

    public boolean isLoggedin(){
        return sharedPreferences.getBoolean(keyLogin,false);
    }

    public void clear(){
        sharedPreferences.edit().clear().apply();
    }
}

