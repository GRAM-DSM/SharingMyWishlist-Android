package com.gram2022.sharingmywishlist_android.SignUp;

public class SignUpRequest {
    String userId;
    String nickName;
    String password;

    public SignUpRequest(String userId, String nickName, String password) {
        this.userId = userId;
        this.nickName = nickName;
        this.password = password;
    }
}
