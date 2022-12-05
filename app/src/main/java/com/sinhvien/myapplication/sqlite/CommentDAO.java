package com.sinhvien.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.sinhvien.myapplication.schemas.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.sinhvien.myapplication.schemas.Comment;

public class CommentDAO {
    DBHelper helper;

    public CommentDAO(Context context) {
        helper = new DBHelper(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCurrentTime() {
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Log.d("current-time", "getCurrentDay is being executed with the current time is: " + datetimeFormatter.format(now));

        return datetimeFormatter.format(now);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean insert(Comment comment, String idUser, String idTour) {
        Log.d("insert-comment", "comment body: " + comment.getBody() + ", idUser: " + idUser + ", idTour:" + idTour);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String currentTime = getCurrentTime();
        values.put("body", comment.getBody());
        values.put("created_at", currentTime);
        values.put("user_id", idUser);
        values.put("tour_id", idTour);
        long row = db.insert("comments", null, values);
        db.close();
        return (row > 0);
    }

    public void retreiveImageFromDB(String userId ) {
        SQLiteDatabase db = helper  .getWritableDatabase();
        String sql = "select * from users where user_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userId});
    }

    @SuppressLint("Range")
    public ArrayList<Comment> getAllByTourId(String tourIdParams) {
        Log.d("get_all_comments", "getAll comments is being executed!");
        ArrayList<Comment> list = new ArrayList<>();

        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from comments where tour_id=?", new String[] { tourIdParams });

        while (cursor.moveToNext()) {
            Comment comment = new Comment();
            String createdAt = cursor.getString(cursor.getColumnIndex("created_at"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String userId = cursor.getString(cursor.getColumnIndex("user_id"));
            String tourId = cursor.getString(cursor.getColumnIndex("tour_id"));
            comment.setCreatedAt(createdAt);
            comment.setBody(body);
            comment.setUserId(userId);
            comment.setTourId(tourId);

            Log.d("myData", "myData created at" + createdAt + ", body: " + body);

            list.add(comment);
        }

        db.close();
        cursor.close();
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<Comment> getAll() {
        Log.d("get_all_comments", "getAll comments is being executed!");
        ArrayList<Comment> list = new ArrayList<>();

        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from comments", null);

        while (cursor.moveToNext()) {
            Comment comment = new Comment();
            String createdAt = cursor.getString(cursor.getColumnIndex("created_at"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String userId = cursor.getString(cursor.getColumnIndex("user_id"));
            String tourId = cursor.getString(cursor.getColumnIndex("tour_id"));
            comment.setCreatedAt(createdAt);
            comment.setBody(body);
            comment.setUserId(userId);
            comment.setTourId(tourId);

            Log.d("myData", "myData created at" + createdAt + ", body: " + body);

            list.add(comment);
        }

        db.close();
        cursor.close();
        return list;
    }

    @SuppressLint("Range")
    public User getCommentByTourId(String tourId) {
        String sql = "select * from comments where tour_id=?";
        User user = new User();
        // Connect to the database
        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, new String[] {tourId});
        if (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("user_id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            byte[] avatarImage = cursor.getBlob(cursor.getColumnIndex("avatar_image"));
            Log.d("data", "id: " + id + ", username: " + username + ", password: " + password);

            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setAvatarImage(avatarImage);
        }

        return user;
    }
}
