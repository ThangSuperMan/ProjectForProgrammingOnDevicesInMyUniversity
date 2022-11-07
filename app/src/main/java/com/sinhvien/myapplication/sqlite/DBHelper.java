package com.sinhvien.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "TravelAppDatabase.db";
    public static final int DB_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("db_on_create", "onCreate is being executed! ");

        // Tables
        String sql = "create table tours " +
                "(" +
                "tour_id integer primary key autoincrement," +
                "title text not null," +
                "body text not null," +
                "image text not null," +
                "timeline text not null," +
                "price integer not null" +
                ")";

        sqLiteDatabase.execSQL(sql);

        // Insert data
        sql = "insert into tours (title, body, image, timeline, price) values ('Tour Tamboo Bo Put, Thailand', 'Tongita-shaped country house located in Icheon Pottery Arts Village. It is a spacious private house with a terrace on the third floor of Sera and other cultural centers, famous for its unique exterior in the two thousand pottery art village that blends very well with nature.', 'tour1', '22-26 Oct', 680)";
        sqLiteDatabase.execSQL(sql);
        sql = "insert into tours (title, body, image, timeline, price) values ('Nha Trang Viet Nam', 'Make the most of wonderful moment like a prince and princess  in the castle with all of everything you need for perfect vacation in the coldest district in central Thailand. The private living space with large balcony is perfect for watching the sunset after a day of sightseeing.\n" +
                "Natural light and fresh air pour into this cozy place.\n" +
                "All the castle and the garden in belongs to you. Glamping for children can be arranged.', 'tour2', '12-18 April', 230)";
        sqLiteDatabase.execSQL(sql);
        sql = "insert into tours (title, body, image, timeline, price) values ('Da Nang, Quang Nam', 'Experience the tropical vibe of Bali by staying in our Arch, a newly built bamboo villa located within the Eco Six Bali resort, just a 20-minute scooter ride from the vibrant centre of Ubud.', 'tour3', '20-22 May', 130)";
        sqLiteDatabase.execSQL(sql);
        sql = "insert into tours (title, body, image, timeline, price) values ('Quang Ngai', 'Enjoy quietness and relaxation in the Arch’s amazingly designed Santorini style infinity pool....', 'tour4', '12-18 July', 550)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        Log.d("db_on_downgrade", "Downgrade is being executed! ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("db_on_upgrade", "onUpgrade is being executed! ");
//        String sql = "drop table if exists tours";
//        sqLiteDatabase.execSQL(sql);
//        onCreate(sqLiteDatabase);
    }
}
