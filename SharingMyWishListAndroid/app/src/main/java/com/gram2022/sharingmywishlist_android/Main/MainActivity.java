package com.gram2022.sharingmywishlist_android.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.Create.CreateActivity;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();
    ActivityMainBinding binding;
    ArrayList<WishAllResponse.WishResponseList> dataList;
    ItemAdapter itemAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Toolbar toolbar_main;
    ActionBar actionBar_main;

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

    private void initSwipeRefreshLayout() {
        binding.swipeRefreshLayoutMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemAdapter.clearWish();
                getWishAll();
                itemAdapter.notifyDataSetChanged();
                binding.swipeRefreshLayoutMain.setRefreshing(false);
            }
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
        sharedPreferences.edit().clear().commit();
    }

    private void initItemAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        itemAdapter = new ItemAdapter(dataList, getBaseContext());

        binding.rvMain.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(dataList, getBaseContext());
        binding.rvMain.setAdapter(itemAdapter);
    }

    private void getWishAll() {
        API api = APIProvider.getInstance().create(API.class);

        api.getAll(SignInActivity.accessToken).enqueue(new Callback<WishAllResponse>() {
            @Override
            public void onResponse(Call<WishAllResponse> call, Response<WishAllResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getWishAll() success!");
                    List<WishAllResponse.WishResponseList> body = response.body().getWishResponseList();
                    if (body != null) {
                        Log.d(TAG, "body : " + body);
                        dataList.addAll(body);
                        itemAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<WishAllResponse> call, Throwable t) {
                Log.e(TAG, "getWishAll() failure..", t);
            }
        });
    }
}