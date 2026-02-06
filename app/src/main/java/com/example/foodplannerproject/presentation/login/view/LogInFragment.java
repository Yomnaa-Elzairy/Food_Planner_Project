package com.example.foodplannerproject.presentation.login.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.data.auth.AuthRepositry;
import com.example.foodplannerproject.presentation.login.presenter.LoginPresenter;
import com.example.foodplannerproject.presentation.login.presenter.LoginPresenterImp;
import com.example.foodplannerproject.presentation.signup.view.SignUpFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LogInFragment extends Fragment implements LoginView{
private View rootView;
    EditText email;
EditText password;
Button loginBtn;
TextView signupLink;
private LoginPresenter loginPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_log_in,container,false);
        email = rootView.findViewById(R.id.email_login);
        password = rootView.findViewById(R.id.password_login);
        loginBtn = rootView.findViewById(R.id.login_btn);
        signupLink = rootView.findViewById(R.id.signup_link);
        loginPresenter = new LoginPresenterImp(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty())
                    email.setError("Email is required");
                else if(password.getText().toString().isEmpty())
                    password.setError("Password is required");
                else
                 loginPresenter.login(email.getText().toString(),password.getText().toString());
            }
        });

        String text = "Dont have an account? SignUp";
        SpannableString spannableString = new SpannableString(text);
        int start = text.indexOf("SignUp");
        int end = start + "SignUp".length();

        ClickableSpan clickableSpan =new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                NavHostFragment.findNavController(LogInFragment.this)
                        .navigate(R.id.action_logInFragment_to_signUpFragment);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);   // underline
                ds.setColor(Color.WHITE);    // white
                ds.setFakeBoldText(true);    // bold
            }
        };

        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signupLink.setText(spannableString);
        signupLink.setMovementMethod(LinkMovementMethod.getInstance());
        return rootView;
    }

    @Override
    public void OnLoginSuccess() {
        if (rootView != null) {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Success 🎉")
                    .setMessage("Logged in successfully")
                    .setPositiveButton("Continue", (dialog, which) -> {
                        NavHostFragment.findNavController(this)
                                .navigate(R.id.action_logInFragment_to_homeFragment);
                    })
                    .show();
        }
    }

    @Override
    public void onLoginFailure(String errorMessage) {
        if (errorMessage.toLowerCase().contains("email")) {
            email.setError(errorMessage);
        } else if (errorMessage.toLowerCase().contains("password")) {
            password.setError(errorMessage);
        } else {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Login Failed")
                    .setMessage(errorMessage)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
}