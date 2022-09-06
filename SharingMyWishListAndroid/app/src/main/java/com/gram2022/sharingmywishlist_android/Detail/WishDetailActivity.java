package com.gram2022.sharingmywishlist_android.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.gram2022.sharingmywishlist_android.Main.ItemAdapter;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.databinding.ActivityDetailBinding;

public class WishDetailActivity extends AppCompatActivity {
    final String TAG = WishDetailActivity.class.getSimpleName();
    ActivityDetailBinding binding;

    int wishId;
    String title;
    String writer;
    String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initWishData();
    }

    private void initWishData() {
        wishId = getIntent().getIntExtra("wishId", 0);
        title = getIntent().getStringExtra("title");
        writer = getIntent().getStringExtra("writer");
        contents = getIntent().getStringExtra("contents");
        Log.d(TAG, "wishId : " + wishId);
        Log.d(TAG, "title : " + title);
        Log.d(TAG, "writer : " + writer);
        Log.d(TAG, "contents : " + contents);
    }
}