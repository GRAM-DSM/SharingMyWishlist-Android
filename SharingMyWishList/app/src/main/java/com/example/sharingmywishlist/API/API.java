package com.example.sharingmywishlist.API;

import com.example.sharingmywishlist.Request.SignInRequest;
import com.example.sharingmywishlist.Request.SignUpRequest;
import com.example.sharingmywishlist.Response.SignInResponse;
import com.example.sharingmywishlist.Response.WishAllResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    // /auth

    // Sign Up
    @POST("/auth/signup")
    Call<Void> signUp(@Body SignUpRequest signUpRequest);

    // Sign In
    @POST("/auth/signin")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);


    // /wish

    // All
    @GET("/wish/all")
    Call<List<WishAllResponse>> getAll(
            @Header("Authorization") String accessToken
    );

}
