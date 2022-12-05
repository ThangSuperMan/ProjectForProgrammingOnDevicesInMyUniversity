package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.sinhvien.myapplication.authentication.Auth;
import com.sinhvien.myapplication.schemas.User;
import com.sinhvien.myapplication.sqlite.UserDAO;
import com.sinhvien.myapplication.utils.Utils;

import java.io.InputStream;

public class SignUpActivity extends AppCompatActivity {

    // Variables
    UserDAO userDAO;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    Uri selectedAvatarImage;

    // UI Components
    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button signUpButton;
    ImageView avatarImageView;
    Button chooseImageButton;
    ConstraintLayout layoutContainer;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        userDAO = new UserDAO(getApplicationContext());

        // Add events listeners
        usernameEditText = (EditText)findViewById(R.id.username);
        passwordEditText = (EditText)findViewById(R.id.password);
        confirmPasswordEditText = (EditText)findViewById(R.id.confirm_password);
        chooseImageButton = (Button)findViewById(R.id.choose_image_button);
        signUpButton = (Button)findViewById(R.id.signup_button);

        setUpOnClickListener();

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
                    if (Auth.isUser) {
                        // New
                        Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                        intentProfile.putExtra("username", Auth.user.getUsername());
                        startActivity(intentProfile);

                        // Old
                        // Go to user's profile
//                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//                        overridePendingTransition(0, 0);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }
            }
            return false;
        });
    }

    private void showMessage(String message) {
        layoutContainer = findViewById(R.id.layout_container);
        Snackbar snackbar = Snackbar.make(layoutContainer, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private boolean checkSamePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return false;
        }
        return true;
    }

    private boolean isUsernameExists(String username) {
        User user = new User();
        user = userDAO.getUserByUsername(username);
        if (user.getId() == null && user.getUsername() == null && user.getPassword() == null) {
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            // Uri selectedImage = data.getData();
            selectedAvatarImage = data.getData();
            avatarImageView = (ImageView) findViewById(R.id.avatar_image_view);
            avatarImageView.setImageURI(selectedAvatarImage);
        }
    }

    private void setUpOnClickListener() {
        chooseImageButton.setOnClickListener((View v) -> {
            Toast.makeText(this, "chooseImageButton just clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, IMAGE_PICK_CODE);
        });

        signUpButton.setOnClickListener((View v) -> {
            User user = new User();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            boolean isSamePassword = checkSamePassword(password, confirmPassword);

            if (!isUsernameExists(user.getUsername())) {
                if (isSamePassword) {
                    Toast.makeText(this, "Please, make sure that password is same now you can sign up", Toast.LENGTH_SHORT).show();

                    // Prepare for insert image
                    try {
                        InputStream iStream = getContentResolver().openInputStream(selectedAvatarImage);
                        byte[] inputData = Utils.getBytes(iStream);
                        user.setAvatarImage(inputData);
                        if(userDAO.insert(user)) {
                            Toast.makeText(this, "Signup successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(this, "Add user totally failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                    }

                } else {
                    Toast.makeText(this, "Password and the confirm password have to be the same!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Sorry, but this user is exists, please choose another one!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}