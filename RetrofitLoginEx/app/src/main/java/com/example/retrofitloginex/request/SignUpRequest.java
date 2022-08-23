package com.example.retrofitloginex.request;

/* 서버에 회원가입 요청을 전송하는 데이터 클래스이다. */

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {

    // userId
    @SerializedName("userId")
    String userId;

    // password
    @SerializedName("password")
    String password;

    // nickName
    @SerializedName("nickName")
    String nickName;

    public SignUpRequest(String userId, String password, String nickName) {
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
    }
}
