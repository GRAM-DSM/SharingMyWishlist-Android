package com.gram2022.sharingmywishlist_android.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    final static String TAG =  SignUpActivity.class.getSimpleName();
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initSignUpButton();
        initGoToSignInTextView();
    }

    private void initGoToSignInTextView() {
        binding.tvSignUpGoToSignIn.setOnClickListener(view -> startIntent(SignInActivity.class));
    }

    private void initSignUpButton() {
        binding.btnSignUpSignUp.setOnClickListener(view -> {
            if (checkTextFormat()) {
                startSignUp(getUserId(), getNickName(), getPassword());
            }
        });
    }

    private boolean checkTextFormat() {
        String errorMessage = getString(R.string.signUp_blankError);

        String userId = getUserId();
        String nickName = getNickName();
        String password = getPassword();
        String repeatPassword = getRepeatPassword();

        binding.textInputLayoutSignUpUserId.setError(null);
        binding.textInputLayoutSignUpNickname.setError(null);
        binding.textInputLayoutSignUpPassword.setError(null);
        binding.textInputLayoutSignUpRepeatPassword.setError(null);

        if (TextUtils.isEmpty(userId)) {
            binding.textInputLayoutSignUpUserId.setError(errorMessage);
        }
        if (TextUtils.isEmpty(nickName)) {
            binding.textInputLayoutSignUpNickname.setError(errorMessage);
        }
        if (TextUtils.isEmpty(password)) {
            binding.textInputLayoutSignUpPassword.setError(errorMessage);
        }
        if (TextUtils.isEmpty(repeatPassword)) {
            binding.textInputLayoutSignUpRepeatPassword.setError(errorMessage);
        }
        if (!password.equals(repeatPassword)) {
            binding.textInputLayoutSignUpPassword.setError(getString(R.string.signUp_repeatPasswordError));
            binding.textInputLayoutSignUpRepeatPassword.setError(getString(R.string.signUp_repeatPasswordError));
        } else {
            return true;
        }
        return false;
    }

    private String getUserId() {
        Editable text = binding.etSignUpUserId.getText();
        return text == null ? "" : text.toString().replace(" ", "");
    }

    private String getNickName() {
        Editable text = binding.etSignUpNickName.getText();
        return text == null ? "" : text.toString().replace(" ", "");
    }

    private String getPassword() {
        Editable text = binding.etSignUpPassword.getText();
        return text == null ? "" : text.toString().replace(" ", "");
    }

    private String getRepeatPassword() {
        Editable text = binding.etSignUpRepeatPassword.getText();
        return text == null ? "" : text.toString().replace(" ", "");
    }

    private void startSignUp(String userId, String nickName, String password) {
        Log.d(TAG, "startSignUp() has called");

        SignUpRequest signUpRequest = new SignUpRequest(userId, nickName, password);
        API api = APIProvider.getInstance().create(API.class);
        api.signUp(signUpRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "signUp succeed, userId : " + userId);
                    startIntent(SignInActivity.class);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e(TAG, "signUp failed", t);
                showSnackBar(getString(R.string.signIn_cannotSignUp));
            }
        });
    }

    private void showSnackBar(String text) {
        Snackbar.make(binding.getRoot(), text, Snackbar.LENGTH_SHORT).show();
    }

    private void startIntent(Class to) {
        Intent intent = new Intent(getBaseContext(), to);
        startActivity(intent);
        finish();
    }
}