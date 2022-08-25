package com.example.sharingmywishlist.Response;

import java.util.List;

public class WishAllResponse {

    // id
    int id;

    // title
    String title;

    // contents
    String contents;

    // writer
    String writer;

    // clear
    boolean clear;

    // color
    String color;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getWriter() {
        return writer;
    }

    public boolean isClear() {
        return clear;
    }

    public String getColor() {
        return color;
    }
}
