package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

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

        // initiate ItemAdapter
        initItemAdapter();

        // getWishAll
        getWishAll();

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