package com.gram2022.sharingmywishlist_android.Detail;

import java.util.List;

public class WishCommentResponse {
    List<CommentResponseList> commentResponseList;

    public List<CommentResponseList> getCommentResponseList() {
        return commentResponseList;
    }

    public class CommentResponseList {
        int id;
        String comment;

        public int getId() {
            return id;
        }

        public String getComment() {
            return comment;
        }
    }
}
