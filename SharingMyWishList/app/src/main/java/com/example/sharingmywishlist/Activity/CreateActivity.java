package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

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

        // initiate Color ClickListener
        initColorClickListener();


    }


    // initiate Color ClickListener
    private void initColorClickListener() {

        Log.d(TAG, "initColorClickListener: ");
        binding.radioGroupCreate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {

                switch (id) {
                    case R.id.btn_create_nor:
                        Log.d(TAG, "nor");
                        break;

                    case R.id.btn_create_red:
                        Log.d(TAG, "red");
                        break;

                    case R.id.btn_create_gre:
                        Log.d(TAG, "blu");
                        break;

                    case R.id.btn_create_blu:
                        Log.d(TAG, "nor");
                        break;
                }
            }
        });

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