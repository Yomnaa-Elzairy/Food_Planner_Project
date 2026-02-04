package com.example.foodplannerproject.presentation.signup.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Spannable;
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
import com.example.foodplannerproject.presentation.signup.presenter.SignUpPresenter;
import com.example.foodplannerproject.presentation.signup.presenter.SignUpPresenterImp;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class SignUpFragment extends Fragment implements SignupView{
    private View rootView;
    EditText email;
    EditText password;
    Button btnSignup;
    TextView loginLink;
    private SignUpPresenter signUpPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_sign_up, container, false);

         email = rootView.findViewById(R.id.email);
         password = rootView.findViewById(R.id.password);
         btnSignup = rootView.findViewById(R.id.signup_btn);
         loginLink = rootView.findViewById(R.id.login_link);
        signUpPresenter = new SignUpPresenterImp(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty())
                    email.setError("Email is required");
                else if(password.getText().toString().isEmpty())
                    password.setError("Password is required");
                else
                    signUpPresenter.signUp(email.getText().toString(),password.getText().toString());
            }
        });


        String text = "Already have an account? Login";
        SpannableString spannableString = new SpannableString(text);
        int start = text.indexOf("Login");
        int end = start + "Login".length();

        ClickableSpan clickableSpan =new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                NavHostFragment.findNavController(SignUpFragment.this)
                        .navigate(R.id.action_signUpFragment_to_logInFragment);
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

        loginLink.setText(spannableString);
        loginLink.setMovementMethod(LinkMovementMethod.getInstance());
       return rootView;
    }

    @Override
    public void OnSignupSuccess() {
        if (rootView != null) {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Success 🎉")
                    .setMessage("Account created successfully")
                    .setPositiveButton("Continue", (dialog, which) -> {
//                        NavHostFragment.findNavController(this)
//                                .navigate(R.id.action_signUpFragment_to_logInFragment);
                    })
                    .show();
        }
    }

    @Override
    public void onSignupFailure(String errorMessage) {
        if (errorMessage.toLowerCase().contains("email")) {
            email.setError(errorMessage);
        } else if (errorMessage.toLowerCase().contains("password")) {
            password.setError(errorMessage);
        } else {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Signup Failed")
                    .setMessage(errorMessage)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
}