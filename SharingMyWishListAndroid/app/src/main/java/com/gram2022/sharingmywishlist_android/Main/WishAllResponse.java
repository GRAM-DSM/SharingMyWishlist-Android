package com.gram2022.sharingmywishlist_android.Main;

import java.util.List;

public class WishAllResponse {
    List<WishResponseList> wishResponseList;

    public List<WishResponseList> getWishResponseList() {
        return wishResponseList;
    }

    public class WishResponseList {
        int id;
        String title;
        String contents;
        String writer;
        boolean clear;
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
}
