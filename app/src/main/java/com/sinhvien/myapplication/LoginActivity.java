package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.myapplication.authentication.Auth;
import com.sinhvien.myapplication.schemas.User;
import com.sinhvien.myapplication.sqlite.UserDAO;

public class LoginActivity extends AppCompatActivity {

    // Variables
    UserDAO userDAO;

    // UI Components
    BottomNavigationView bottomNavigationView;
    Button loginBtn;
    TextView signUpText;
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Init userDao
        userDAO = new UserDAO(getApplicationContext());

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener((View v) -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            boolean inActiveUser = isUser(username, password);
            if (inActiveUser) {
                Auth.isUser = true;
                Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
                Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                intentProfile.putExtra("username", username);
                startActivity(intentProfile);
            } else {
                Toast.makeText(this, "Sorry but your username or password are incorrect!", Toast.LENGTH_SHORT).show();
            }
        });

        signUpText = (TextView)findViewById(R.id.signupText);
        signUpText.setOnClickListener((View v) -> {
             startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
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

    // Functions
    private boolean isUser(String username, String password) {
        User user = new User();
        user = userDAO.getUserByUsername(username);
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }
}