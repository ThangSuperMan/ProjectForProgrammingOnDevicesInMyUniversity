package com.sinhvien.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.sinhvien.myapplication.schemas.User;

interface InterfaceUserDAO {
    public boolean insert(User user);
    public User getUserByUsername(String usernameParams);
}

public class UserDAO implements InterfaceUserDAO {
    DBHelper helper;

    public UserDAO(Context context) {
        helper = new DBHelper(context);
    }

    @Override
    public boolean insert(User user) {
        Log.d("insert-user", "username: " + user.getUsername() + "-" + "password: " + user.getPassword());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        long row = db.insert("users", null, values);
        db.close();
        return (row > 0);
    }

    @SuppressLint("Range")
    public User getUserByUsername(String usernameParams) {
        Log.d("get-user", "getUserByUsername is being executed with usernameParams: " + usernameParams);
        String sql = "select * from users where username=?";
        User user = new User();
        Log.d("my-query", sql);

        // Connect to the database
        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, new String[] {usernameParams});
        if (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("user_id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            Log.d("data", "id: " + id + ", username: " + username + ", password: " + password);

            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
        }

        return user;
    }
}
