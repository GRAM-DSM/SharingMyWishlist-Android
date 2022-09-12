package com.gram2022.sharingmywishlist_android.API;

import com.gram2022.sharingmywishlist_android.Create.CreateRequest;
import com.gram2022.sharingmywishlist_android.Detail.WishCommentRequest;
import com.gram2022.sharingmywishlist_android.Detail.WishCommentResponse;
import com.gram2022.sharingmywishlist_android.Main.WishAllResponse;
import com.gram2022.sharingmywishlist_android.SignIn.SignInRequest;
import com.gram2022.sharingmywishlist_android.SignIn.SignInResponse;
import com.gram2022.sharingmywishlist_android.SignUp.SignUpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @DELETE("/wish/delete/{id}")
    Call<Void> delete(@Header("Authorization") String accessToken, @Path("id") int id);

    @GET("/comment/{id}")
    Call<WishCommentResponse> getComment(@Header("Authorization") String accessToken, @Path("id") int id);

    @POST("/comment/{id}")
    Call<Void> postComment(@Header("Authorization") String accessToken, @Path("id") int id, @Body WishCommentRequest wishCommentRequest);
}
