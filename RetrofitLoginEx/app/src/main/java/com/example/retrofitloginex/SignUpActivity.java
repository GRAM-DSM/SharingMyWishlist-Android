package com.example.retrofitloginex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitloginex.request.SignUpRequest;
import com.example.retrofitloginex.response.SignUpResponse;
import com.example.retrofitloginex.retrofit.RetrofitAPI;
import com.example.retrofitloginex.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    // TAG
    private static final String TAG = "MainActivity";

    // RetrofitAPI
    private RetrofitAPI retrofitAPI;

    // EditText
    private EditText et_signIn_name; // 이름
    private EditText et_signIn_email; // 이메일
    private EditText et_signIn_password; // 비밀번호

    // Button
    private Button btn_signUp_signUp; // Sign Up

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // initiate UI
        initUI();

        // initiate Sign Up Button ClickListener
        initSignUpButtonClickListener();

        // initiate Retrofit
        initRetrofit();
    }


    // initiate Retrofit
    private void initRetrofit() {
        retrofitAPI = RetrofitClient.getClient().create(RetrofitAPI.class);
    }


    // initiate UI
    private void initUI() {

        // Button
        btn_signUp_signUp = findViewById(R.id.btn_signUp_signUp); // Sign Up

        // EditText
        et_signIn_name = findViewById(R.id.et_signIn_name); // 이름
        et_signIn_email = findViewById(R.id.et_signIn_email); // 이메일
        et_signIn_password = findViewById(R.id.et_signIn_password); // 비밀번호
    }


    // initiate Sign Up Button
    private void initSignUpButtonClickListener() {

        // 데이터
        String name = et_signIn_name.getText().toString(); // 이름
        String email = et_signIn_email.getText().toString(); // 이메일
        String password = et_signIn_password.getText().toString(); // 비밀번호

        btn_signUp_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start Sign Up
                startSignUp(new SignUpRequest(name, email, password));
            }
        });
    }


    // Start Sign Up
    private void startSignUp(SignUpRequest data) {
        retrofitAPI.signup(data).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.d(TAG, "Sign Up Request Succeed");
                Toast.makeText(SignUpActivity.this, "Sign Up Succeed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.e(TAG, "Sign Up Request Failed", t);

            }
        });
    }
}