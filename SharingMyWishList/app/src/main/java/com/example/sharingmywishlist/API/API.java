package com.example.sharingmywishlist.API;

import com.example.sharingmywishlist.Request.ClearRequest;
import com.example.sharingmywishlist.Request.CreateRequest;
import com.example.sharingmywishlist.Request.SignInRequest;
import com.example.sharingmywishlist.Request.SignUpRequest;
import com.example.sharingmywishlist.Response.SignInResponse;
import com.example.sharingmywishlist.Response.WishAllResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    // Clear
    @PATCH("/wish/clear/{id}")
    Call<ResponseBody> clear(
            @Header("Authorization") String accessToken,
            @Path("id") int id
    );

    // Create
    @POST("/wish/create")
    Call<Void> create(
            @Header("Authorization") String accessToken,
            @Body CreateRequest createRequest
    );
}
