package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sinhvien.myapplication.schemas.User;
import com.sinhvien.myapplication.sqlite.UserDAO;

public class SignUpActivity extends AppCompatActivity {

    // Variables
    UserDAO userDAO;

    // UI Components
    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        userDAO = new UserDAO(getApplicationContext());

        // add events listeners
        usernameEditText = (EditText)findViewById(R.id.username);
        passwordEditText = (EditText)findViewById(R.id.password);
        confirmPasswordEditText = (EditText)findViewById(R.id.confirm_password);
        signUpButton = (Button)findViewById(R.id.signup_button);

        setUpOnClickListener();
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

    private void setUpOnClickListener() {
        signUpButton.setOnClickListener((View v) -> {
            User user = new User();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            boolean isSamePassword = checkSamePassword(password, confirmPassword);

            if (!isUsernameExists(user.getUsername())) {
//                Toast.makeText(this, "Yeah this user does not exist", Toast.LENGTH_SHORT).show();
                if (isSamePassword) {
                    Toast.makeText(this, "Please, make sure that password is same now you can sign up", Toast.LENGTH_SHORT).show();
                    if (userDAO.insert(user)) {
                        Toast.makeText(this, "Add user successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0, 0);
                    } else {
                        Toast.makeText(this, "Add user totally failed!", Toast.LENGTH_SHORT).show();
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