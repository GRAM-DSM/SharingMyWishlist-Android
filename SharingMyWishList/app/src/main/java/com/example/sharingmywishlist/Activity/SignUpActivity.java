package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sharingmywishlist.API.API;
import com.example.sharingmywishlist.API.APIProvider;
import com.example.sharingmywishlist.Request.SignUpRequest;
import com.example.sharingmywishlist.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    // TAG
    private final static String TAG = "SignUpActivity";
    // Binding
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // btn_signUp_signUp ClickListener
        signUpButtonClickListener();

        // btn_signUp_goToSignIn ClickListener
        goToSignInClickListener();
    }


    // goToSignInClickListener
    private void goToSignInClickListener() {

        // log
        Log.d(TAG, "goToSignInClickListener() has called");

        binding.btnSignUpGoToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    // btn_signUp_signUp ClickListener
    private void signUpButtonClickListener() {

        // log
        Log.d(TAG, "signUpButtonClickListener() has called");

        binding.btnSignUpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Sign Up
                signUp();
            }
        });
    }


    // Sign Up
    private void signUp() {

        // log
        Log.d(TAG, "signUp() has called");

        // 값을 담을 String
        String userId = binding.etSignUpUserId.getText().toString(); // userId
        String password = binding.etSignUpPassword.getText().toString(); // password
        String nickName = binding.etSignUpNickName.getText().toString(); // nickName

        SignUpRequest signUpRequest = new SignUpRequest(userId, password, nickName);

        API api = APIProvider.getInstance().create(API.class);

        api.signUp(signUpRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Sign Up Succeed! " + "userId : " + userId);
                    Toast.makeText(SignUpActivity.this, "Sign up succeed, Please log in", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Sign Up Failed", t);
                Toast.makeText(SignUpActivity.this, "Sign up failed..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}