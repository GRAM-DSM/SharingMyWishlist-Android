package com.example.sharingmywishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.example.sharingmywishlist.API.API;
import com.example.sharingmywishlist.API.APIProvider;
import com.example.sharingmywishlist.R;
import com.example.sharingmywishlist.Request.CreateRequest;
import com.example.sharingmywishlist.databinding.ActivityCreateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    // TAG
    private static final String TAG = "CreateActivity";

    // binding
    private ActivityCreateBinding binding;

    // Color
    private String selectedColor = "wish-nor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initiate Cancel ClickListener
        initCancelClickListener();

        // initiate Color ClickListener
        initColorClickListener();

        // initiate Add ClickListener
        initAddClickListener();
    }


    // initiate Add ClickListener
    private void initAddClickListener() {

        binding.tvCreateCommitCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //createRequest
                create();
            }
        });
    }


    // create Wish
    private void create() {

        Log.d(TAG, "create() has called");

        // String
        String title = binding.etCreateTitle.getText().toString(); // title
        String contents = binding.etCreateContent.getText().toString(); // contents
        String color = this.selectedColor; // color

        // CreateRequest
        CreateRequest createRequest = new CreateRequest(title, contents, color);

        API api = APIProvider.getInstance().create(API.class);

        api.create(SignInActivity.accessToken, createRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "create Success, title : " + title);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "create Failure..");
            }
        });
    }


    // initiate Color ClickListener
    private void initColorClickListener() {

        Log.d(TAG, "initColorClickListener: ");
        binding.radioGroupCreate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {

                switch (id) {
                    default:
                    case R.id.btn_create_nor:
                        Log.d(TAG, "nor");
                        selectedColor = "wish-nor";
                        break;

                    case R.id.btn_create_red:
                        Log.d(TAG, "red");
                        selectedColor = "wish-red";
                        break;

                    case R.id.btn_create_gre:
                        Log.d(TAG, "gre");
                        selectedColor = "wish-gre";
                        break;

                    case R.id.btn_create_blu:
                        Log.d(TAG, "blu");
                        selectedColor = "wish-blu";
                        break;
                }
                Log.d(TAG, "checked : " + selectedColor);
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