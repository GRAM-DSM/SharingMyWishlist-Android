package com.gram2022.sharingmywishlist_android.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

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
    final String TAG = this.getClass().getSimpleName();
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
        binding.tvSignInGoToSignUp.setOnClickListener(view -> {
            startIntent(SignUpActivity.class);
        });
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
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful()) {
                    accessToken = "Bearer " + response.body().getAccessToken();
                    Log.d(TAG, "signIn succeed, userId : " + userId + ", accessToken : " + accessToken);
                    startIntent(MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e(TAG, "signIn failed", t);
                showSnackBar(getString(R.string.signIn_cannotSignIn));
                clearSharedPreferences();
            }
        });
    }

    private void clearSharedPreferences() {
        sharedPreferences.edit().clear().commit();
    }

    private void showSnackBar(String text) {
        Snackbar.make(binding.getRoot(), text, Snackbar.LENGTH_SHORT).show();
    }

    private String getUserId() {
        return binding.etSignInUserId.getText().toString().replace(" ", "");
    }

    private String getPassword() {
        return binding.etSignInPassword.getText().toString().replace(" ", "");
    }

    private void startIntent(Class to) {
        Intent intent = new Intent(getBaseContext(), to);
        startActivity(intent);
        finish();
    }
}