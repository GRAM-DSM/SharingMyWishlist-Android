package com.gram2022.sharingmywishlist_android.Detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.databinding.ActivityDetailBinding;

public class WishDetailActivity extends AppCompatActivity {
    final String TAG = WishDetailActivity.class.getSimpleName();
    ActivityDetailBinding binding;
    Toolbar toolbar_detail;
    ActionBar actionBar_detail;

    int wishId;
    String title;
    String writer;
    String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();

        initWishData();
        initDetails();
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