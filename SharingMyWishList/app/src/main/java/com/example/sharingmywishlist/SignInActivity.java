package com.example.sharingmywishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sharingmywishlist.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    // TAG
    private final static String TAG = "SignInActivity";

    // Binding
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sign In Button ClickListener
        signInButtonClickListener();

        // Go To Sign Up ClickListener
        goToSignUpClickListener();
    }


    // Go To Sign Up ClickListener
    private void goToSignUpClickListener() {

        // log
        Log.d(TAG, "goToSignUnClickListener() has called");

        binding.btnSignInGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    // Sign In Button ClickListener
    private void signInButtonClickListener() {

        // log
        Log.d(TAG, "signInButtonClickListener() has called");

        binding.btnSignInSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Sign In
                signIn();
            }
        });
    }


    // Sign In
    private void signIn() {

    }
}