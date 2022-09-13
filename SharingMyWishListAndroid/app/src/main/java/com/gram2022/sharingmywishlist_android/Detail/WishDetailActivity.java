package com.gram2022.sharingmywishlist_android.Detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivityDetailBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishDetailActivity extends AppCompatActivity {
    final String TAG = WishDetailActivity.class.getSimpleName();
    ActivityDetailBinding binding;
    Toolbar toolbar_detail;
    ActionBar actionBar_detail;
    ArrayList<WishCommentResponse.CommentResponseList> commentList;
    DetailItemAdapter detailItemAdapter;
    API api;

    int wishId;
    String title;
    String writer;
    String contents;
    String createdAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();

        commentList = new ArrayList<>();

        api = APIProvider.getInstance().create(API.class);

        initWishData();
        initDetails();
        initItemAdapter();
        getComment();
        initPostCommentButton();
        initSwipeRefreshLayout();
    }

    private void initPostCommentButton() {
        binding.btnDetailPostComment.setOnClickListener(v -> {
            if (checkTextFormat()) {
                WishCommentRequest wishCommentRequest = new WishCommentRequest(wishId, Objects.requireNonNull(binding.etDetailComment.getText()).toString());
                postComment(wishCommentRequest);
                checkTextFormat();
                binding.etDetailComment.setText("");
            }
        });
    }

    private boolean checkTextFormat() {
        String errorMessage = getString(R.string.detail_comment_formatError);
        String detailComment = getDetailComment();

        binding.textInputLayoutDetailComment.setError(null);

        if (TextUtils.isEmpty(detailComment)) {
            binding.textInputLayoutDetailComment.setError(errorMessage);
        } else {
            return true;
        }
        return false;
    }

    private void initItemAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        binding.rvDetail.setLayoutManager(linearLayoutManager);
        detailItemAdapter = new DetailItemAdapter(commentList, getBaseContext());
        binding.rvDetail.setAdapter(detailItemAdapter);
    }

    private void getComment() {
        api.getComment(SignInActivity.accessToken, wishId).enqueue(new Callback<WishCommentResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<WishCommentResponse> call, @NonNull Response<WishCommentResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getComment() success!");
                    assert response.body() != null;
                    List<WishCommentResponse.CommentResponseList> body = response.body().getCommentResponseList();
                    if (body != null) {
                        Log.d(TAG, "body : " + body);
                        commentList.addAll(body);
                        detailItemAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WishCommentResponse> call, @NonNull Throwable t) {
            }
        });
    }

    private void initSwipeRefreshLayout() {
        binding.swipeRefreshLayoutDetail.setOnRefreshListener(() -> {
            refreshComment();
            binding.swipeRefreshLayoutDetail.setRefreshing(false);
        });
    }

    private void postComment(WishCommentRequest wishCommentRequest) {
        api.postComment(SignInActivity.accessToken, wishId, wishCommentRequest).enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "response Success!");
                    refreshComment();
                } else {
                    Log.d(TAG, "response failure, " + response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refreshComment() {
        detailItemAdapter.clearComment();
        getComment();
        detailItemAdapter.notifyDataSetChanged();
    }


    private void initToolbar() {
        toolbar_detail = binding.toolbarDetail;
        setSupportActionBar(toolbar_detail);
        actionBar_detail = getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_detail_back) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDetails() {
        binding.tvDetailTitle.setText(title);
        binding.tvDetailWriter.setText(writer);
        binding.tvRvMainItemContents.setText(contents);
        binding.tvDetailCreatedAt.setText(createdAt);
    }

    private void initWishData() {
        wishId = getIntent().getIntExtra("wishId", 0);
        title = getIntent().getStringExtra("title");
        writer = getIntent().getStringExtra("writer");
        contents = getIntent().getStringExtra("contents");
        createdAt = getIntent().getStringExtra("createdAt").substring(0, 10);
        Log.d(TAG, "wishId : " + wishId);
        Log.d(TAG, "title : " + title);
        Log.d(TAG, "writer : " + writer);
        Log.d(TAG, "contents : " + contents);
        Log.d(TAG, "createdAt : " + createdAt);
    }

    private String getDetailComment() {
        Editable text = binding.etDetailComment.getText();
        return text == null ? "" : text.toString().replace(" ", "");
    }
}