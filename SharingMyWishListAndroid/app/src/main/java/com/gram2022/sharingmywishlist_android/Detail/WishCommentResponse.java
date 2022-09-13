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
        String nickName;
        String createdAt;

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getId() {
            return id;
        }

        public String getComment() {
            return comment;
        }

        public String getNickName() {
            return nickName;
        }
    }
}
