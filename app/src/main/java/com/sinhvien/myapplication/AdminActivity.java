package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.myapplication.authentication.Auth;
import com.sinhvien.myapplication.schemas.Tour;
import com.sinhvien.myapplication.sqlite.TourDAO;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    // Variables
    TourDAO tourDAO;

    // UI Components
    BottomNavigationView bottomNavigationView;
    ListView manageToursListView;
    Button addTourButton;
//    Button chooseImageButton;
//    ImageView uploadImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//        uploadImageView = (ImageView) findViewById(R.id.upload_image_view);
//
//        ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//            // After choose an item
//            @Override
//            public void onActivityResult(Uri result) {
//                Toast.makeText(AdminActivity.this, "Laucher just run", Toast.LENGTH_SHORT).show();
//                uploadImageView.setImageURI(result);
//            }
//        });
//
//        chooseImageButton = (Button) findViewById(R.id.choose_image_upload_button);
//        chooseImageButton.setOnClickListener((View v) -> {
//            Toast.makeText(this, "Just click the button", Toast.LENGTH_SHORT).show();
//            launcher.launch("image/*");
//        });

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        manageToursListView = (ListView) findViewById(R.id.manage_tour_list_view);
        tourDAO = new TourDAO(AdminActivity.this);

        ArrayList<Tour> tours = tourDAO.getAll();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tours);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_admin);

        bottomNavigationView.setOnItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_item_explore:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_login:
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                    overridePendingTransition(0, 0);
                    // New
                    if (Auth.isUser) {
                        Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                        intentProfile.putExtra("username", Auth.user.getUsername());
                        startActivity(intentProfile);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                    return true;
                case R.id.menu_item_admin:
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });

        addEventListeners();
    }

    // Functions
    private void addEventListeners() {
        addTourButton = (Button) findViewById(R.id.add_tour_button);
        addTourButton.setOnClickListener((View v) -> {
            Toast.makeText(this, "Just click the button", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), TourInsertActivity.class));
        });

//        chooseImageButton = (Button) findViewById(R.id.choose_image_upload_button);
//        chooseImageButton.setOnClickListener((View v) -> {
//            Toast.makeText(this, "Just click the button", Toast.LENGTH_SHORT).show();
//        });
//
//        uploadImageView = (ImageView) findViewById(R.id.upload_image_view);
    }
}