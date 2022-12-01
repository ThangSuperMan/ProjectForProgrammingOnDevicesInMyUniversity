package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.myapplication.authentication.Auth;

public class ProfileActivity extends AppCompatActivity {

    // UI Components
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Toast.makeText(this, "Profile page here!", Toast.LENGTH_SHORT).show();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_login);

        bottomNavigationView.setOnItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_item_wishlists:
                    startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_admin:
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_login:
                    Toast.makeText(this, "Login item clicked", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Aut.isUser" + Auth.isUser, Toast.LENGTH_SHORT).show();
                    if (Auth.isUser) {
                        // Go to user's profile
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }
            }
            return false;
        });

    }
}