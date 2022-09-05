package com.gram2022.sharingmywishlist_android.Create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gram2022.sharingmywishlist_android.Main.MainActivity;
import com.gram2022.sharingmywishlist_android.R;
import com.gram2022.sharingmywishlist_android.databinding.ActivityCreateBinding;

public class CreateActivity extends AppCompatActivity {

    ActivityCreateBinding binding;
    Toolbar toolbar_create;
    ActionBar actionBar_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create_cancel:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.menu_create_ok:
                startCreate(binding.etCreateTitle.getText().toString(), binding.etCreateContents.getText().toString());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startCreate(String title, String contents) {
        // TODO if response success, start intent
    }
}