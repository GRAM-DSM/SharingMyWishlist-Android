package com.gram2022.sharingmywishlist_android.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.gram2022.sharingmywishlist_android.Create.CreateActivity;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("StaticFieldLeak")
public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.getSimpleName();
    static ArrayList<WishAllResponse.WishResponseList> dataList;
    static ItemAdapter itemAdapter;
    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Toolbar toolbar_main;
    ActionBar actionBar_main;

    public static void getWishAll() {
        API api = APIProvider.getInstance().create(API.class);

        api.getAll(SignInActivity.accessToken).enqueue(new Callback<WishAllResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<WishAllResponse> call, @NonNull Response<WishAllResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getWishAll() success!");
                    assert response.body() != null;
                    List<WishAllResponse.WishResponseList> body = response.body().getWishResponseList();
                    if (body != null) {
                        Log.d(TAG, "body : " + body);
                        dataList.addAll(body);
                        itemAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WishAllResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "getWishAll() failure..", t);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initSharedPreferences();
        initToolbar();

        dataList = new ArrayList<>();

        getWishAll();
        initItemAdapter();
        initSwipeRefreshLayout();
    }

    private void initSharedPreferences() {
        sharedPreferences = getSharedPreferences("autoSignIn", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initSwipeRefreshLayout() {
        binding.swipeRefreshLayoutMain.setOnRefreshListener(() -> {
            itemAdapter.clearWish();
            getWishAll();
            itemAdapter.notifyDataSetChanged();
            binding.swipeRefreshLayoutMain.setRefreshing(false);
        });
    }

    private void initToolbar() {
        toolbar_main = binding.toolbarMain;
        setSupportActionBar(toolbar_main);
        actionBar_main = getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_add:
                Intent createIntent = new Intent(getBaseContext(), CreateActivity.class);
                startActivity(createIntent);
                finish();
                return true;
            case R.id.menu_main_signOut:
                Intent signOutIntent = new Intent(getBaseContext(), SignInActivity.class);
                startActivity(signOutIntent);
                finish();
                clearSharedPreferences();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }

    private void initItemAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        itemAdapter = new ItemAdapter(dataList, getBaseContext());

        binding.rvMain.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(dataList, getBaseContext());
        binding.rvMain.setAdapter(itemAdapter);
    }
}