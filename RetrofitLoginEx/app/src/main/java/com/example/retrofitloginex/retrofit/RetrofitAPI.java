package com.example.retrofitloginex.retrofit;

/* 서버에서 받을 API 형식을 정의하는 인터페이스이다. */

import com.example.retrofitloginex.request.SignInRequest;
import com.example.retrofitloginex.request.SignUpRequest;
import com.example.retrofitloginex.response.SignInResponse;
import com.example.retrofitloginex.response.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    // Sign In
    @POST("/signin")
    Call<SignInResponse> signin(@Body SignInRequest request);

    // Sign Up
    @POST("/signup")
    Call<SignUpResponse> signup(@Body SignUpRequest request);

}
