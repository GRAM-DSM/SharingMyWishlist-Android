package com.gram2022.sharingmywishlist_android.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gram2022.sharingmywishlist_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}