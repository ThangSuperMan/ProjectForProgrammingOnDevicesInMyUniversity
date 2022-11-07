package com.sinhvien.myapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sinhvien.myapplication.classes.Tour;

public class OwnerModel {
    private static SQLiteDatabase db;

//    public OwnerModel (Context context, String dbName) {
//        Log.d("OWNER_MODEL", "TourDao: constuctor is being exectued!");
//        DBHelper dbHelper = new DBHelper(context);
//
//        // Connect to the sqlite
//        db = dbHelper.getReadableDatabase();
//    }

    public long insert(Tour tour) {
        ContentValues values = new ContentValues();
        values.put("tour_id", tour.getId());
        values.put("title", tour.getTitle());
        values.put("body", tour.getBody());
        values.put("timeline", tour.getTimeline());
        values.put("image_name", tour.getImage());
        values.put("price", tour.getPrice());
        values.put("price", tour.getPrice());

        return db.insert("tours", null, values);
    }
}
