package com.example.sharingmywishlist.Request;

public class CreateRequest {

    // userId
    String userId;

    // contents
    String contents;

    // color
    String color;

    public CreateRequest(String userId, String contents, String color) {
        this.userId = userId;
        this.contents = contents;
        this.color = color;
    }
}
