package com.example.foodplannerproject.presentation.login.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.foodplannerproject.R;
import com.example.foodplannerproject.presentation.login.presenter.LoginPresenter;
import com.example.foodplannerproject.presentation.login.presenter.LoginPresenterImp;
import com.google.android.material.snackbar.Snackbar;

public class LogInFragment extends Fragment implements LoginView {

    private View rootView;
    private EditText email;
    private EditText password;
    private Button loginBtn;
    private TextView signupLink;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_log_in, container, false);

        email = rootView.findViewById(R.id.email_login);
        password = rootView.findViewById(R.id.password_login);
        loginBtn = rootView.findViewById(R.id.login_btn);
        signupLink = rootView.findViewById(R.id.signup_link);
        progressBar = rootView.findViewById(R.id.login_progress);

        loginPresenter = new LoginPresenterImp(this, requireContext());

        loginBtn.setOnClickListener(v -> {
            if (email.getText().toString().isEmpty())
                email.setError("Email is required");
            else if (password.getText().toString().isEmpty())
                password.setError("Password is required");
            else
                loginPresenter.login(
                        email.getText().toString(),
                        password.getText().toString()
                );
        });

        setupSignupLink();
        return rootView;
    }

    private void setupSignupLink() {
        String text = "Dont have an account? SignUp";
        SpannableString spannableString = new SpannableString(text);

        int start = text.indexOf("SignUp");
        int end = start + "SignUp".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                NavHostFragment.findNavController(LogInFragment.this)
                        .navigate(R.id.action_logInFragment_to_signUpFragment);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(Color.WHITE);
                ds.setFakeBoldText(true);
            }
        };

        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signupLink.setText(spannableString);
        signupLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (loginPresenter.isUserLoggedin()) {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_logInFragment_to_homeFragment);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        loginBtn.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        loginBtn.setEnabled(true);
    }

    @Override
    public void onLoginSuccess() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_logInFragment_to_homeFragment);
    }

    @Override
    public void onLoginFailure(String errorMessage) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onNoInternet() {
        Snackbar.make(rootView,
                        "No internet connection",
                        Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", v -> {
                    loginPresenter.login(
                            email.getText().toString(),
                            password.getText().toString()
                    );
                })
                .show();

    }

}
