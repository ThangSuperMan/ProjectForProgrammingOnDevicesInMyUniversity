package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.myapplication.authentication.Auth;
import com.sinhvien.myapplication.schemas.User;
import com.sinhvien.myapplication.sqlite.UserDAO;
import com.sinhvien.myapplication.utils.Utils;

public class ProfileActivity extends AppCompatActivity {

    // Variables
    User user;

    // UI Components
    BottomNavigationView bottomNavigationView;
    TextView usernameTextView;
    TextView changePasswordTextView;
    ImageView profileImageView;
    Button logoutButton;

    // DB
    UserDAO userDao;

    // Lifecycles
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Add event listeners
        usernameTextView = (TextView) findViewById(R.id.username_text_view);
        profileImageView = (ImageView) findViewById(R.id.profile_image_view);
        logoutButton = (Button) findViewById(R.id.logout_button);
        changePasswordTextView = (TextView) findViewById(R.id.change_password_text_view);

        changePasswordTextView.setOnClickListener((View) -> {
            Toast.makeText(this, "changePasswordTextView just called", Toast.LENGTH_SHORT).show();
            handleChangePasswordDialog();
        });

        logoutButton.setOnClickListener((View) -> {
            Auth.isUser = false;
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
            Toast.makeText(this, "Successfully logout!", Toast.LENGTH_SHORT).show();
        });

        // Init DB
        userDao = new UserDAO(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username;
            username = extras.getString("username");
            user = new User();
            user = userDao.getUserByUsername(username);

            // Add the user info the the global scope
            Auth.user = user;

            Bitmap avatarImageBitmap = Utils.getImage(Auth.user.getAvatarImage());
            // setDataForTheUI(user.getUsername(), avatarImageBitmap );
             setDataForTheUI(Auth.user.getUsername(), avatarImageBitmap);
        } else {
            Toast.makeText(this, "extra from the Login intent is null", Toast.LENGTH_SHORT).show();
        }

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

    // Functions
    private void handleChangePasswordDialog() {
        Toast.makeText(this, "handleChangePasswordDialog is being executed!", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        View view = LayoutInflater.from(ProfileActivity.this).inflate(
            R.layout.layout_change_password_diaglog, (ConstraintLayout)findViewById(R.id.layout_dialog_container)
        );
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        // Make the background of alert dialog transparent
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        alertDialog.show();
    }

    private void setDataForTheUI(String username, Bitmap avatarImage) {
        usernameTextView.setText(user.getUsername());
        profileImageView.setImageBitmap(Utils.getImage(user.getAvatarImage()));
    }
}
