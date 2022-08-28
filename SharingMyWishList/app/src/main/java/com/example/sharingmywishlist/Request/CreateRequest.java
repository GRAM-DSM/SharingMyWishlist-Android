package com.example.sharingmywishlist.Request;

public class CreateRequest {

    // userId
    String title;

    // contents
    String contents;

    // color
    String color;

    public CreateRequest(String title, String contents, String color) {
        this.title = title;
        this.contents = contents;
        this.color = color;
    }
}
