package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.myapplication.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener((View v) -> {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_login);
        bottomNavigationView.setOnItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_item_explore:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_wishlists:
                    startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_admin:
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_login:
                    return true;
            }
            return false;
        });

    }
}