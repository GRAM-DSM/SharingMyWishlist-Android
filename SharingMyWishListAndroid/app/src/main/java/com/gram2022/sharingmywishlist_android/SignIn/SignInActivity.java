package com.gram2022.sharingmywishlist_android.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignUp.SignUpActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivitySignInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();
    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initSignInButton();
        initGoToSignUpTextView();
    }

    private void initGoToSignUpTextView() {
        binding.tvSignInGoToSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initSignInButton() {
        binding.btnSignInSignIn.setOnClickListener(view -> {
            if (checkTextFormat()) {
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

        if (userId.equals("") || userId.equals(null)) {
            binding.textInputLayoutSignInUserId.setHelperText(errorMessage);
        }
        if (password.equals("") || password.equals(null)) {
            binding.textInputLayoutSignInPassword.setHelperText(errorMessage);
        } else {
            return true;
        }
        return false;
    }

    private void startSignIn(String userId, String password) {
        Log.d(TAG, "startSignIn() has called");

        SignInRequest signInRequest = new SignInRequest(userId, password);
        API api = APIProvider.getInstance().create(API.class);
        api.signIn(signInRequest).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "signIn succeed, userId : " + userId + ", accessToken : " + response.body().accessToken);
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e(TAG, "signIn failed", t);
            }
        });
    }

    private String getUserId() {
        return binding.etSignInUserId.getText().toString().replace(" ", "");
    }

    private String getPassword() {
        return binding.etSignInPassword.getText().toString().replace(" ", "");
    }
}