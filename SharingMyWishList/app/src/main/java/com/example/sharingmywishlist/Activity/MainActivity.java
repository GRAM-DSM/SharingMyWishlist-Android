package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sharingmywishlist.API.API;
import com.example.sharingmywishlist.API.APIProvider;
import com.example.sharingmywishlist.ItemAdapter;
import com.example.sharingmywishlist.R;
import com.example.sharingmywishlist.Response.SignInResponse;
import com.example.sharingmywishlist.Response.WishAllResponse;
import com.example.sharingmywishlist.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // TAG
    private final static String TAG = "MainActivity";

    // Binding
    private ActivityMainBinding binding;

    // Context
    private Context context;

    // SharedPreferences
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    // ItemAdapter
    private ItemAdapter adapter;
    // LayoutManager
    private LinearLayoutManager linearLayoutManager;


    // WishAllResponse data list
    private ArrayList<WishAllResponse> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Context
        context = getBaseContext();

        // SharedPreferences
        preferences = context.getSharedPreferences("signIn", Context.MODE_PRIVATE);
        editor = preferences.edit(); // Editor

        // initiate ItemAdapter
        initItemAdapter();

        // initiate Sign Out ClickListener
        initSignOutClickListener();

        // getWishAll
        getWishAll();
    }


    // initiate Sign Out ClickListener
    private void initSignOutClickListener() {
        binding.tvMainSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Sign Out
                signOut();
            }
        });
    }


    // Sign Out
    private void signOut() {

        // Disable Auto Sign In
        editor.putBoolean("autoSignIn", false);
        editor.commit();

        // Start Intent To Sign In Activity
        Intent intent = new Intent(context, SignInActivity.class);
        startActivity(intent);
        finish();
    }


    // getWishAll
    private void getWishAll() {

        // API
        API api = APIProvider.getInstance().create(API.class);
        // Send API Request
        api.getAll(SignInActivity.accessToken).enqueue(new Callback<List<WishAllResponse>>() {
            @Override
            public void onResponse(Call<List<WishAllResponse>> call, Response<List<WishAllResponse>> response) {

                if (response.isSuccessful()) {
                    dataSet.addAll(response.body());
                    adapter.notifyDataSetChanged();

                    // log
                    Log.d(TAG, "response succeed!");
                }
            }

            @Override
            public void onFailure(Call<List<WishAllResponse>> call, Throwable t) {
                // log
                Log.e(TAG, "response failed..", t);
            }
        });
    }


    // initiate ItemAdapter
    private void initItemAdapter() {

        // ArrayList
        dataSet = new ArrayList<>();

        // LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        // set LayoutManager
        binding.rvMain.setLayoutManager(linearLayoutManager);

        // Adapter
        adapter = new ItemAdapter(dataSet);
        // Set Adapter
        binding.rvMain.setAdapter(adapter);
    }
}