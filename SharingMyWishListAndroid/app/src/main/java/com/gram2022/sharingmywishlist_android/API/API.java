package com.gram2022.sharingmywishlist_android.API;

import com.gram2022.sharingmywishlist_android.Create.CreateRequest;
import com.gram2022.sharingmywishlist_android.Main.WishAllResponse;
import com.gram2022.sharingmywishlist_android.SignIn.SignInRequest;
import com.gram2022.sharingmywishlist_android.SignIn.SignInResponse;
import com.gram2022.sharingmywishlist_android.SignUp.SignUpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @POST("/auth/signin")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);

    @POST("/auth/signup")
    Call<Void> signUp(@Body SignUpRequest signUpRequest);

    @GET("/wish/all")
    Call<WishAllResponse> getAll(@Header("Authorization") String accessToken);

    @PATCH("/wish/clear/{id}")
    Call<Void> clear(@Header("Authorization") String accessToken, @Path("id") int id);

    @POST("/wish/create")
    Call<Void> create(@Header("Authorization") String accessToken, @Body CreateRequest createRequest);
}
