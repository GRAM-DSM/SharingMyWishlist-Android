package com.example.retrofitloginex.response;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    // accessToken
    @SerializedName("accessToken")
    String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
