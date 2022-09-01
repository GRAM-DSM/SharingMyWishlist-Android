package com.gram2022.sharingmywishlist_android.API;

import com.gram2022.sharingmywishlist_android.SignIn.SignInRequest;
import com.gram2022.sharingmywishlist_android.SignIn.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("/auth/signin")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);
}
