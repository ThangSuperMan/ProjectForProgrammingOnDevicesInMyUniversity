package com.sinhvien.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.sinhvien.myapplication.MainActivity;
import com.sinhvien.myapplication.classes.Tour;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

interface InterfaceTourDao {
    public ArrayList<Tour> getAll();
    public boolean insert(Tour tour);
    public Tour getTourById(String id);
}

public class TourDAO implements InterfaceTourDao {
    DBHelper helper;

    public TourDAO(Context context) {
        helper = new DBHelper(context);
    }

//    @SuppressLint("Range")
//    public ArrayList<Tour> getTour(String sql, String...selectArgs) {
//        Log.d("get_tour", "getTour is being executed!");
//        ArrayList<Tour> tours = new ArrayList<>();
//        SQLiteDatabase db = helper.getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql, selectArgs);

//        while (cursor.moveToNext()) {
//            Tour tour = new Tour();
//            String tourId = cursor.getString(cursor.getColumnIndex("id"));
//            String title = cursor.getString(cursor.getColumnIndex("title"));
//            String body = cursor.getString(cursor.getColumnIndex("body"));
//            String timeline = cursor.getString(cursor.getColumnIndex("timeline"));
//            String image = cursor.getString(cursor.getColumnIndex("image "));
//
//            Log.d("id: " + tourId + " title: " + title, "getTour: ");
//            tours.add(tour);
//        }
//
//        return tours;
//    }

    @Override
    @SuppressLint("Range")
    public Tour getTourById(String tourId) {
        Log.d("get_tour_by_id", "getTourById is being executed!");
        String sql = "select * from tours where tour_id=" + tourId;
        Tour tour = new Tour();

        // Connect to the database
        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToNext();
            String id = cursor.getString(cursor.getColumnIndex("tour_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String timeline = cursor.getString(cursor.getColumnIndex("timeline"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            String price = cursor.getString(cursor.getColumnIndex("price"));

            tour.setId(id);
            tour.setTitle(title);
            tour.setBody(body);
            tour.setTimeline(timeline);
            tour.setImage(image);
            tour.setPrice(Integer.parseInt(price));

        Log.d("my_tour", "Tour after selected: " + tour.getTitle() + "id: " + tour.getId());

        return tour;
    }

    @Override
    @SuppressLint("Range")
    public ArrayList<Tour> getAll() {
        Log.d("get_all", "getAll is being executed!");
        ArrayList<Tour> list = new ArrayList<>();

        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from tours", null);

        while (cursor.moveToNext()) {
            Tour tour = new Tour();
            String tourId = cursor.getString(cursor.getColumnIndex("tour_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String timeline = cursor.getString(cursor.getColumnIndex("timeline"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            String price = cursor.getString(cursor.getColumnIndex("price"));

            tour.setId(tourId);
            tour.setTitle(title);
            tour.setBody(body);
            tour.setTimeline(timeline);
            tour.setImage(image);
            tour.setPrice(Integer.parseInt(price));

            list.add(tour);
        }

        cursor.close();
        return list;
    }

    @Override
    public boolean insert(Tour tour) {
        String priceString = String.valueOf(tour.getPrice());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tour_id", tour.getId());
        values.put("title", tour.getTitle());
        values.put("body", tour.getBody());
        values.put("timeline", tour.getTimeline());
        values.put("image", tour.getImage());
        values.put("price", tour.getPrice());
        long row = db.insert("tours", null, values);
        Log.d("row", "row value: " + row);
        return (row > 0);
    }
}
