package com.gram2022.sharingmywishlist_android.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.Main.MainActivity;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();
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
        binding.tvSignUpGoToSignIn.setOnClickListener(view -> {
            startIntent(SignInActivity.class);
        });
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

        binding.textInputLayoutSignUpUserId.setHelperText(null);
        binding.textInputLayoutSignUpNickname.setHelperText(null);
        binding.textInputLayoutSignUpPassword.setHelperText(null);
        binding.textInputLayoutSignUpRepeatPassword.setHelperText(null);

        if (userId.equals("") || userId.equals(null)) {
            binding.textInputLayoutSignUpUserId.setHelperText(errorMessage);
        }
        if (nickName.equals("") || nickName.equals(null)) {
            binding.textInputLayoutSignUpNickname.setHelperText(errorMessage);
        }
        if (password.equals("") || password.equals(null)) {
            binding.textInputLayoutSignUpPassword.setHelperText(errorMessage);
        }
        if (repeatPassword.equals("") || repeatPassword.equals(null)) {
            binding.textInputLayoutSignUpRepeatPassword.setHelperText(errorMessage);
        }
        if (!password.equals(repeatPassword)) {
            binding.textInputLayoutSignUpPassword.setHelperText(getString(R.string.signUp_repeatPasswordError));
            binding.textInputLayoutSignUpRepeatPassword.setHelperText(getString(R.string.signUp_repeatPasswordError));
        } else {
            return true;
        }
        return false;
    }

    private String getUserId() {
        return binding.etSignUpUserId.getText().toString().replace(" ", "");
    }

    private String getNickName() {
        return binding.etSignUpNickName.getText().toString().replace(" ", "");
    }

    private String getPassword() {
        return binding.etSignUpPassword.getText().toString().replace(" ", "");
    }

    private String getRepeatPassword() {
        return binding.etSignUpRepeatPassword.getText().toString().replace(" ", "");
    }

    private void startSignUp(String userId, String nickName, String password) {
        Log.d(TAG, "startSignUp() has called");

        SignUpRequest signUpRequest = new SignUpRequest(userId, nickName, password);
        API api = APIProvider.getInstance().create(API.class);
        api.signUp(signUpRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "signUp succeed, userId : " + userId);
                    startIntent(MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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