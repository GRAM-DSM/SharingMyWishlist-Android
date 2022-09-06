package com.gram2022.sharingmywishlist_android.Detail;

import java.util.List;

public class DetailResponse {
    int id;
    String title;
    String contents;
    String writer;
    boolean clear;
    String color;

    List<Comments> comments;

    private class Comments {
        int id;
        String nickName;
        String comment;

        public int getId() {
            return id;
        }

        public String getNickName() {
            return nickName;
        }

        public String getComment() {
            return comment;
        }
    }

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

    public List<Comments> getComments() {
        return comments;
    }
}
