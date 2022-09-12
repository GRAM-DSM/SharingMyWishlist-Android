package com.gram2022.sharingmywishlist_android.SignIn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.Main.MainActivity;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignUp.SignUpActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivitySignInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    public static String accessToken;
    final static String TAG = SignInActivity.class.getSimpleName();
    ActivitySignInBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initSignInButton();
        initGoToSignUpTextView();
        initSharedPreferences();
        initAutoSignIn();
    }

    private void initGoToSignUpTextView() {
        binding.tvSignInGoToSignUp.setOnClickListener(view -> startIntent(SignUpActivity.class));
    }

    private void initSignInButton() {
        binding.btnSignInSignIn.setOnClickListener(view -> {
            if (checkTextFormat()) {
                if (binding.chkSignInKeepSignIn.isChecked()) {
                    Log.d(TAG, "Auto sign in checked, method started");
                    editor.putBoolean("autoSignIn", true);
                    editor.putString("userId", getUserId());
                    editor.putString("password", getPassword());
                    editor.commit();
                }
                startSignIn(getUserId(), getPassword());
            }
        });
    }

    private boolean checkTextFormat() {
        String errorMessage = getString(R.string.signUp_blankError);

        String userId = getUserId();
        String password = getPassword();

        binding.textInputLayoutSignInUserId.setHelperText(null);
        binding.textInputLayoutSignInPassword.setHelperText(null);

        if (TextUtils.isEmpty(userId)) {
            binding.textInputLayoutSignInUserId.setHelperText(errorMessage);
        }
        if (TextUtils.isEmpty(password)) {
            binding.textInputLayoutSignInPassword.setHelperText(errorMessage);
        } else {
            return true;
        }
        return false;
    }

    private void initAutoSignIn() {
        if (sharedPreferences.getBoolean("autoSignIn", false)) {
            Log.d(TAG, "Auto sign in started");
            startSignIn(sharedPreferences.getString("userId", null), sharedPreferences.getString("password", null));
            Log.d(TAG, "userId : " + sharedPreferences.getString("userId", null));
        }

    }

    private void initSharedPreferences() {
        sharedPreferences = getSharedPreferences("autoSignIn", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void startSignIn(String userId, String password) {
        Log.d(TAG, "startSignIn() has called");

        SignInRequest signInRequest = new SignInRequest(userId, password);
        API api = APIProvider.getInstance().create(API.class);
        api.signIn(signInRequest).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        accessToken = "Bearer " + response.body().getAccessToken();
                        Log.d(TAG, "signIn succeed, userId : " + userId + ", accessToken : " + accessToken);
                        startIntent(MainActivity.class);
                    } else {
                        Log.d(TAG, "signIn body is null!");
                    }
                } else {
                    Log.d(TAG, "onResponse not successful, error body is : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "signIn failed", t);
                showSnackBar(getString(R.string.signIn_cannotSignIn));
                clearSharedPreferences();
            }
        });
    }

    private void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }

    private void showSnackBar(String text) {
        Snackbar.make(binding.getRoot(), text, Snackbar.LENGTH_SHORT).show();
    }

    private String getUserId() {
        Editable text = binding.etSignInUserId.getText();
        return text == null ? "" : text.toString().replace(" ", "");
    }

    private String getPassword() {
        Editable text = binding.etSignInPassword.getText();
        return text == null ? "" : text.toString().replace(" ", "");
    }

    private void startIntent(Class to) {
        Intent intent = new Intent(getBaseContext(), to);
        startActivity(intent);
        finish();
    }
}