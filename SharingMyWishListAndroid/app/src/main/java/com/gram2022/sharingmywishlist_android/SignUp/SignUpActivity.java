package com.gram2022.sharingmywishlist_android.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivitySignUpBinding;

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
            Intent intent = new Intent(getBaseContext(), SignInActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initSignUpButton() {
        binding.btnSignUpSignUp.setOnClickListener(view -> {
            if (checkTextFormat()) {
                Toast.makeText(this, "Success !!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkTextFormat() {
        String errorMessage = getString(R.string.signUp_blankError);

        String userId = getUserId();
        String nickName = getNickName();
        String password = getPassword();
        String repeatPassword = getRepeatPassword();

        Log.d(TAG, userId);
        Log.d(TAG, nickName);
        Log.d(TAG, password);
        Log.d(TAG, repeatPassword);

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

    private void setHelperText(TextInputLayout view, String text) {
        view.setHelperText(text);
    }

    private void startSignUp(String userId, String nickName, String password) {
        Log.d(TAG, "startSignUp() has called");
    }
}