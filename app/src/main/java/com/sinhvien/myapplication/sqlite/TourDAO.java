package com.sinhvien.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.sinhvien.myapplication.schemas.Tour;
import java.util.ArrayList;

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

    @Override
    @SuppressLint("Range")
    public Tour getTourById(String tourId) {
        String sql = "select * from tours where tour_id=?";
        Tour tour = new Tour();

        // Connect to the database
        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,new String[]{tourId});
        if (cursor.moveToNext()) {
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

        }
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

        db.close();
        cursor.close();
        return list;
    }

    @Override
    public boolean insert(Tour tour) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tour_id", tour.getId());
        values.put("title", tour.getTitle());
        values.put("body", tour.getBody());
        values.put("timeline", tour.getTimeline());
        values.put("image", tour.getImage());
        values.put("price", tour.getPrice());
        long row = db.insert("tours", null, values);
        db.close();
        return (row > 0);
    }
}
