package com.example.sharingmywishlist.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    // Sign Up
    @POST("/auth/signup")
    Call<Void> signUp(@Body SignUpRequest signUpRequest);
}
