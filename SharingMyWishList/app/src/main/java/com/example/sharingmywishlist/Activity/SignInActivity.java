package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sharingmywishlist.API.API;
import com.example.sharingmywishlist.API.APIProvider;
import com.example.sharingmywishlist.Request.SignInRequest;
import com.example.sharingmywishlist.Request.SignUpRequest;
import com.example.sharingmywishlist.Response.SignInResponse;
import com.example.sharingmywishlist.databinding.ActivitySignInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    // TAG
    private final static String TAG = "SignInActivity";

    // accessToken
    private static String accessToken;

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

        // log
        Log.d(TAG, "signIn() has called");

        // 값을 담을 String
        String userId = binding.etSignInUserId.getText().toString(); // userId
        String password = binding.etSignInPassword.getText().toString(); // password

        SignInRequest signInRequest = new SignInRequest(userId, password);

        API api = APIProvider.getInstance().create(API.class);

        api.signIn(signInRequest).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful()) {

                    // log
                    Log.d(TAG, "Sign In Succeed! " + "userId : " + userId);

                    // accessToken
                    accessToken = response.body().getAccessToken();
                    Toast.makeText(SignInActivity.this, "Login succeed!", Toast.LENGTH_SHORT).show();

                    // Start MainActivity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

                // log
                Log.d(TAG, "Sign In failed..");

                Toast.makeText(SignInActivity.this, "Login failed..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}