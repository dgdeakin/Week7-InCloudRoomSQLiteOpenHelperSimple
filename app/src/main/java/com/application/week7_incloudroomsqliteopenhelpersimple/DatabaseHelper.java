package com.application.week7_incloudroomsqliteopenhelpersimple;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String MY_DATABASE = "mydatabase.db";
    public static final int VERSION = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, MY_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE mytable (id INTEGER PRIMARY KEY, name TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS mytable");
    }
}
