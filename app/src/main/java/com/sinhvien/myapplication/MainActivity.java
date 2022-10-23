package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sinhvien.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variables
    String tourTitles[] = { "Vaala, Finland", "Enonkoski, Finland", "Calaca, Philippin", "Canada Dream"};
    int tourImages[] = { R.drawable.tour1, R.drawable.tour2, R.drawable.tour3, R.drawable.tour4 };
    String timelines[] = { "22-26 Oct", "19-04 April", "20-04 May", "21-09 October" };
    int totalPrice[] = { 680, 230, 120, 340 };
    ArrayList<Tour> tours = new ArrayList<Tour>();
    ListView tourListView;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set explore activity selected
        binding.bottomNavigationView.setSelectedItemId(R.id.menu_item_explore);

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

//        replaceFragment(new ExploreFragment());

//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//
//            switch (item.getItemId()) {
//                case R.id.menu_item_explore:
//                    replaceFragment(new ExploreFragment());
//                    Toast.makeText(this, "Explore", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.menu_item_wishlists:
//                    replaceFragment(new WishlistsFragment());
//                    Intent wishlistsIntent = new Intent(getApplicationContext(), DetailTourActivity.class);
//                    startActivity(wishlistsIntent);
//                    Toast.makeText(this, "Wishlists", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.menu_item_trips:
//                    replaceFragment(new TripsFragment());
//                    Toast.makeText(this, "Trips", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.menu_item_inbox:
//                    replaceFragment(new InboxFragment());
//                    Toast.makeText(this, "Inbox", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.menu_item_login:
//                    replaceFragment(new LoginFragment());
//                    Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//
//            return true;
//        });

        setupDate();
        setUpList();
        setUpOnClickListener();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Replace the frame_layout and push the fragment
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void setupDate() {
        for (int i = 0 ; i < tourTitles.length; i++) {
            String id = Integer.toString(i);
            //    public Tour(String id, String name, int image, String timeline, int totalPrice) {
            Tour tour = new Tour(id, tourTitles[i], tourImages[i], timelines[i], totalPrice[i]);
            tours.add(tour);
        }
    }

    private void setUpList() {
        tourListView = (ListView) findViewById(R.id.toursListView);

        TourAdapter tourAdapter = new TourAdapter(getApplicationContext(), 0, tours);
        tourListView.setAdapter(tourAdapter);
    }

    private void setUpOnClickListener() {
        tourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Tour selectedTour = (Tour) (tourListView.getItemAtPosition(position));
                Intent showDetailIntent = new Intent(getApplicationContext(), DetailTourActivity.class);
                showDetailIntent.putExtra("id", selectedTour.getId());
                startActivity(showDetailIntent);
            }
        });
    }
}