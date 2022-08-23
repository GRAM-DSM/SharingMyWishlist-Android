package com.example.retrofitloginex.request;

/* 서버에 로그인 요청을 전송하는 데이터 클래스이다. */

import com.google.gson.annotations.SerializedName;

public class SignInRequest {

    // userId
    @SerializedName("userId")
    String userId;

    // password
    @SerializedName("password")
    String password;

    public SignInRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
