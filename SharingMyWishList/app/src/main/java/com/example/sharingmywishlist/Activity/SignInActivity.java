package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
<<<<<<< Updated upstream
    public static String accessToken;
=======
<<<<<<< Updated upstream
    private static String accessToken;
>>>>>>> Stashed changes

=======
    public static String accessToken;
    // Context
    private Context context;
    // SharedPreference
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
>>>>>>> Stashed changes
    // Binding
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // context
        context = getApplicationContext();

        // SharedPreferences
        preferences = context.getSharedPreferences("signIn", Context.MODE_PRIVATE);
        editor = preferences.edit(); // Editor

        // Auto SignIn
        autoSignIn();

        // TESTLOG
        Log.d(TAG, "junj auto Login : " + preferences.getBoolean("autoSignIn", false));
        Log.d(TAG, "junj userId : " + preferences.getString("userId", "empty"));
        Log.d(TAG, "junj password : " + preferences.getString("password", "empty"));

        // Sign In Button ClickListener
        signInButtonClickListener();

        // Go To Sign Up ClickListener
        goToSignUpClickListener();
    }


    // Auto Sign In
    private void autoSignIn() {

        // get Auto SignIn
        if (preferences.getBoolean("autoSignIn", false)) {

            Log.d(TAG, "autoSignIn called");
        }
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

                // 값을 담아 전달할 String
                String userId = binding.etSignInUserId.getText().toString(); // userId
                String password = binding.etSignInPassword.getText().toString(); // password
                
                // Set Sign In to Automatically
                if (binding.chkSignInKeepLoggedIn.isChecked()) {

                    Log.d(TAG, "signInButtonClickListener true");

                    // Put Auto Sign In
                    editor.putBoolean("autoSignIn", true);

                    // Put user Information
                    editor.putString("userId", userId); // userId
                    editor.putString("password", password); // password

                    // commit
                    editor.commit();
                }

                // Sign In
                signIn(userId, password);
            }
        });
    }


    // Sign In
    private void signIn(String _userId, String _password) {

        // log
        Log.d(TAG, "signIn() has called");

        // 값을 담을 String
        String userId = _userId; // userId
        String password = _password; // password

        SignInRequest signInRequest = new SignInRequest(userId, password);

        API api = APIProvider.getInstance().create(API.class);

        api.signIn(signInRequest).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful()) {

                    // accessToken
                    accessToken = response.body().getAccessToken();
                    Toast.makeText(SignInActivity.this, "Sign In succeed!", Toast.LENGTH_SHORT).show();

                    // log
                    Log.d(TAG, "Sign In Succeed! " + "userId : " + userId + ", accessToken : " + accessToken);


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

                Toast.makeText(SignInActivity.this, "Sign In failed..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}