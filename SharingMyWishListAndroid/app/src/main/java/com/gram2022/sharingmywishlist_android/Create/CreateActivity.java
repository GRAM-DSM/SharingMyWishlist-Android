package com.gram2022.sharingmywishlist_android.Create;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gram2022.sharingmywishlist_android.API.API;
import com.gram2022.sharingmywishlist_android.API.APIProvider;
import com.gram2022.sharingmywishlist_android.Main.MainActivity;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.SignIn.SignInActivity;
import com.gram2022.sharingmywishlist_android.databinding.ActivityCreateBinding;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    ActivityCreateBinding binding;
    Toolbar toolbar_create;
    ActionBar actionBar_create;

    String color = "wish-nor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        initColorRadioButton();
    }

    @SuppressLint("NonConstantResourceId")
    private void initColorRadioButton() {
        binding.radioGroupCreate.setOnCheckedChangeListener((group, id) -> {
            switch (id) {
                default:
                case R.id.radio_create_nor:
                    color = getString(R.string.create_wish_nor);
                    break;
                case R.id.radio_create_red:
                    color = getString(R.string.create_wish_red);
                    break;
                case R.id.radio_create_gre:
                    color = getString(R.string.create_wish_gre);
                    break;
                case R.id.radio_create_blu:
                    color = getString(R.string.create_wish_blu);
                    break;
            }
        });
    }

    private void initToolbar() {
        toolbar_create = binding.toolbarCreate;
        setSupportActionBar(toolbar_create);
        actionBar_create = getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create_cancel:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.menu_create_ok:
                if (TextUtils.isEmpty(binding.etCreateTitle.getText())) {
                    binding.textInputLayoutCreateTitle.setHelperText(getString(R.string.create_title_formatError));
                    return false;
                }
                if (TextUtils.isEmpty(binding.etCreateContents.getText())) {
                    binding.etCreateContents.setText("");
                }
                startCreate(Objects.requireNonNull(binding.etCreateTitle.getText()).toString(), Objects.requireNonNull(binding.etCreateContents.getText()).toString(), color);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startCreate(String title, String contents, String color) {
        CreateRequest createRequest = new CreateRequest(title, contents, color);
        API api = APIProvider.getInstance().create(API.class);
        api.create(SignInActivity.accessToken, createRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), getString(R.string.create_response_success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), getString(R.string.create_response_failure), Toast.LENGTH_SHORT).show();
            }
        });
    }
}