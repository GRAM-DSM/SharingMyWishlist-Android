package com.example.sharingmywishlist.Request;

public class SignInRequest {

    // userId
    String userId;
    // password
    String password;

    public SignInRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
