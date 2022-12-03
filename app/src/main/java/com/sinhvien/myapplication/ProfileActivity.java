package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
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

    // Varibles
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

        Toast.makeText(this, "onCreate: profile activity", Toast.LENGTH_SHORT).show();

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
            showPopup();
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

            Toast.makeText(this, "user_id: " + user.getId() + ", username: " + user.getUsername() + ", password: " + user.getPassword(), Toast.LENGTH_SHORT).show();
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
    private void showPopup() {
        PopupWindow window = new PopupWindow(this);
        int marginLeft = 20;
        int marginRight = 20;
        // window.setWidth((WindowManager.LayoutParams.MATCH_PARENT - (marginLeft + marginRight)));
//      window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setWidth(200);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenH = displayMetrics.heightPixels;
        int screenW = displayMetrics.widthPixels;
        int xPosition = 20;
        Toast.makeText(this, "screenH: " + screenH, Toast.LENGTH_SHORT).show();
        // 2208
        window.setHeight(screenH / 4);
        window.setWidth(screenW - (marginLeft + marginRight));

        window.setTouchable(true);
        window.setFocusable(true);

        EditText text = new EditText(this);
        Button button = new Button(this);
        text.setText("Touch it, it doesn't crash");
        text.setTextColor(Color.WHITE);

        window.setContentView(text);
        window.setContentView(button);
        window.showAtLocation(text, Gravity.CENTER, 0, 0);
    }

    private void setDataForTheUI(String username, Bitmap avatarImage) {
        usernameTextView.setText(user.getUsername());
        profileImageView.setImageBitmap(Utils.getImage(user.getAvatarImage()));
    }
}