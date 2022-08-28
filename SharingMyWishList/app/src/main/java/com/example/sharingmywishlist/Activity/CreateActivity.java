package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sharingmywishlist.R;
import com.example.sharingmywishlist.databinding.ActivityCreateBinding;

public class CreateActivity extends AppCompatActivity {

    // TAG
    private static final String TAG = "CreateActivity";

    // binding
    private ActivityCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initiate Cancel ClickListener
        initCancelClickListener();


    }


    // initiate Cancel ClickListener
    private void initCancelClickListener() {
        binding.tvCreateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // back to MainActivity
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}