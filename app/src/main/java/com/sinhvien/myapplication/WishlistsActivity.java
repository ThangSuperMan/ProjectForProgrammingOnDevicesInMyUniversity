package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sinhvien.myapplication.databinding.ActivityMainBinding;

public class WishlistsActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlists);
        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set explore activity selected
        binding.bottomNavigationView.setSelectedItemId(R.id.menu_item_wishlists);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_explore:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    Toast.makeText(this, "Explore", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_item_wishlists:
                    startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                    overridePendingTransition(0, 0);
                    Toast.makeText(this, "Wishlists", Toast.LENGTH_SHORT).show();
                    return true;
            }

            return true;
        });
    }
}