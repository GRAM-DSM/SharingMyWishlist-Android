package com.example.sharingmywishlist.API;

public class SignUpRequest {

    // userId
    String userId;
    // password
    String password;
    // nickName
    String nickName;

    public SignUpRequest(String userId, String password, String nickName) {
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
    }
}
