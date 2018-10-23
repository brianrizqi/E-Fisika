package com.blue.miqdad.e_fisika.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import static com.blue.miqdad.e_fisika.DB.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbefisika";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_TABLE = String.format("CREATE TABLE %s" +
            "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_BOOK,
            BookColumns._ID,
            BookColumns.NAMA,
            BookColumns.RAW_ID);

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.TABLE_BOOK);
        onCreate(db);
    }
}
