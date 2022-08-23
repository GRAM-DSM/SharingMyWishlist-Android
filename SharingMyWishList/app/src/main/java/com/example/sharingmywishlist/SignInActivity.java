package com.example.sharingmywishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sharingmywishlist.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    // Binding
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}