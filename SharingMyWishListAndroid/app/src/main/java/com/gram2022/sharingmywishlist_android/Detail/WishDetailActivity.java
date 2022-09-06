package com.gram2022.sharingmywishlist_android.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.databinding.ActivityDetailBinding;

public class WishDetailActivity extends AppCompatActivity {
    final String TAG = WishDetailActivity.class.getSimpleName();
    ActivityDetailBinding binding;
    int wishId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAG, "" + wishId);

    }
}