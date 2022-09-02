package com.gram2022.sharingmywishlist_android.Main;

public class WishAllResponse {

    WishResponseList wishResponseList;

    private class WishResponseList {
        int id;
        String title;
        String contents;
        String writer;
        boolean clear;
        String color;
    }
}
