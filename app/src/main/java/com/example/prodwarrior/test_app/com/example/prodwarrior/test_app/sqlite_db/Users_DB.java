package com.example.prodwarrior.test_app.com.example.prodwarrior.test_app.sqlite_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prodwarrior on 02/09/2016.
 */
public class Users_DB extends SQLiteOpenHelper {

    private static final String Create = "CREATE TABLE USUARIOS (" +
                                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        "nombres VARCHAR(100) NOT NULL," +
                                        "apellido VARCHAR(100) NOT NULL," +
                                        "telefono VARCHAR(12)," +
                                        "email VARCHAR(100)," +
                                        "photo_path TEXT);";
    private static final String Destroy = "DROP TABLE IF EXISTS USUARIOS;";
    public Users_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Destroy);
        db.execSQL(Create);
    }
}
