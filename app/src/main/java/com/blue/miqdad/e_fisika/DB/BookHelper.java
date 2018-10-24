package com.blue.miqdad.e_fisika.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.blue.miqdad.e_fisika.Models.Book;

import java.util.ArrayList;

import static com.blue.miqdad.e_fisika.DB.DatabaseContract.*;

public class BookHelper {

    private static final String TAG = BookHelper.class.getSimpleName();
    private static String DATABASE_TABLE = DatabaseContract.TABLE_BOOK;
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase sqLiteDatabase;

    public BookHelper(Context context){
        this.context = context;
    }

    public BookHelper open(){
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<Book> getBooks(){
        ArrayList<Book> list = new ArrayList<Book>();

        Cursor cursor = sqLiteDatabase.query(TABLE_BOOK, null, null, null, null, null, BookColumns._ID + " ASC", null);
        Log.d(TAG, "getBooks: cursor size : " + cursor.getCount());
        cursor.moveToFirst();
        Book book;
        do {
            book = new Book();
            book.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BookColumns._ID)));
            book.setName(cursor.getString(cursor.getColumnIndexOrThrow(BookColumns.NAMA)));
            book.setRaw_id(cursor.getLong(cursor.getColumnIndexOrThrow(BookColumns.RAW_ID)));

            list.add(book);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        cursor.close();
        return list;
    }

    public long insert(Book book){
        ContentValues contentValues = new ContentValues();
//        contentValues.put(BookColumns._ID, book.getId());
        contentValues.put(BookColumns.NAMA, book.getName());
        contentValues.put(BookColumns.RAW_ID, book.getRaw_id());
        return sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
    }

    public int update(Book book){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BookColumns._ID, book.getId());
        contentValues.put(BookColumns.NAMA, book.getName());
        contentValues.put(BookColumns.RAW_ID, book.getRaw_id());
        return sqLiteDatabase.update(DATABASE_TABLE, contentValues, BookColumns._ID + "= '"+ book.getId() +"'", null);
    }

    public int delete(int id){
        return sqLiteDatabase.delete(DATABASE_TABLE, BookColumns._ID + "= '" + id + "'", null);
    }

    public void beginTransaction(){
        sqLiteDatabase.beginTransaction();
    }

    public void insertTransaction(Book book){
        String sql = String.format("INSERT INTO %s(%s, %s) VALUES(?, ?)", TABLE_BOOK, BookColumns.NAMA, BookColumns.RAW_ID);
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, book.getName());
        statement.bindLong(2, book.getRaw_id());
        statement.execute();
        statement.clearBindings();
    }

    public void endTransaction(){
        sqLiteDatabase.endTransaction();
    }

}
