package com.gram2022.sharingmywishlist_android.Detail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gram2022.sharingmywishlist_android.databinding.RvDetailCommentItemBinding;

import java.util.ArrayList;

public class DetailItemAdapter extends RecyclerView.Adapter<DetailItemAdapter.ViewHolder> {
    static String TAG = DetailItemAdapter.class.getSimpleName();

    ArrayList<WishCommentResponse.CommentResponseList> commentList;

    Context context;

    public DetailItemAdapter(ArrayList<WishCommentResponse.CommentResponseList> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
        Log.d(TAG, "DetailItemAdapter has called");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvDetailCommentItemBinding binding = RvDetailCommentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.bindItem(commentList.get(position));
    }

    public void clearComment() {
        commentList.clear();
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RvDetailCommentItemBinding itemBinding;

        public ViewHolder(@NonNull RvDetailCommentItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }

        void bindItem(WishCommentResponse.CommentResponseList comment) {

            Log.d(TAG, "==========");
            Log.d(TAG, "id : " + comment.getId());
            Log.d(TAG, "nickName : " + comment.getNickName());
            Log.d(TAG, "comment : " + comment.getComment());
            Log.d(TAG, "createdAt : " + comment.getCreatedAt());

            itemBinding.tvRvDetailCommentComment.setText(comment.getComment());
            itemBinding.tvRvDetailCommentWriter.setText(comment.getNickName());
            itemBinding.tvRvDetailCommentCreatedAt.setText(comment.getCreatedAt().substring(0, 10));
        }
    }
}
