package com.gram2022.sharingmywishlist_android.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();
    private ActivityMainBinding binding;
    private ArrayList<WishAllResponse.WishResponseList> dataList;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initItemAdapter();

        getWishAll();

    }

    private void initItemAdapter() {
        dataList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        ItemAdapter itemAdapter = new ItemAdapter(dataList, getBaseContext());

        binding.rvMain.setLayoutManager(linearLayoutManager);
        binding.rvMain.setAdapter(itemAdapter);
    }

    private void getWishAll() {
        API api = APIProvider.getInstance().create(API.class);



        api.getAll(SignInActivity.accessToken).enqueue(new Callback<WishAllResponse.WishResponseList>() {
            @Override
            public void onResponse(Call<WishAllResponse.WishResponseList> call, Response<WishAllResponse.WishResponseList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, gson.fromJson(response.toString(), WisAllResponse.WishResponseList.class));
                }
            }

            @Override
            public void onFailure(Call<WishAllResponse.WishResponseList> call, Throwable t) {

            }
        });
    }
}