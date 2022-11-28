package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sinhvien.myapplication.schemas.Tour;
import com.sinhvien.myapplication.sqlite.TourDAO;

public class TourInsertActivity extends AppCompatActivity {
    // Variables
    TourDAO tourDAO;

    // UI Components
    EditText title;
    EditText body;
    EditText timeline;
    EditText price;
    EditText image;
    Button buttonAddTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_insert);

        Toast.makeText(this, "onCreate in insert page", Toast.LENGTH_SHORT).show();

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tourDAO = new TourDAO(getApplicationContext());

        addEventListeners();
    }

    public void addEventListeners() {
        title = (EditText) findViewById(R.id.title_edit_text);
        body = (EditText) findViewById(R.id.content_edit_text);
        timeline = (EditText) findViewById(R.id.timeline_edit_text);
        image = (EditText) findViewById(R.id.image_edit_text);
        price = (EditText) findViewById(R.id.price_edit_text);
        buttonAddTour = (Button) findViewById(R.id.add_tour_button);

        buttonAddTour.setOnClickListener((View -> {
            Toast.makeText(this, "add tour btn", Toast.LENGTH_SHORT).show();
            Tour tour = new Tour();
            tour.setTitle(title.getText().toString());
            tour.setBody(body.getText().toString());
            tour.setTimeline(timeline.getText().toString());
            tour.setImage(image.getText().toString());
            Toast.makeText(this, price.getText().toString(), Toast.LENGTH_SHORT).show();
            tour.setPrice(Integer.parseInt(price.getText().toString()));

            if (tourDAO.insert(tour)) {
                Toast.makeText(this, "Add tour successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Add tour totally failed!", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}