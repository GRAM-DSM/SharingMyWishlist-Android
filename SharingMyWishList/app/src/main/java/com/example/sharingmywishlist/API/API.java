package com.example.sharingmywishlist.API;

import com.example.sharingmywishlist.Request.SignInRequest;
import com.example.sharingmywishlist.Request.SignUpRequest;
import com.example.sharingmywishlist.Response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    // Sign Up
    @POST("/auth/signup")
    Call<Void> signUp(@Body SignUpRequest signUpRequest);

    // Sign In
    @POST("/auth/signin")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);
}
