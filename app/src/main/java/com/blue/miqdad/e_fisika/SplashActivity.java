package com.blue.miqdad.e_fisika;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.blue.miqdad.e_fisika.DB.BookHelper;
import com.blue.miqdad.e_fisika.Models.AppPreference;
import com.blue.miqdad.e_fisika.Models.Book;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    public static final String[] names = {"Buku satu", "Buku dua"};
    public static final long[] id_raws = {R.raw.a_room_with_a_view_morrison, R.raw.a_room_with_a_view_morrison};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new LoadDatabaseAsyncTask().execute();
    }

    private class LoadDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {

        BookHelper helper;
        AppPreference preference;

        @Override
        protected void onPreExecute() {
            helper = new BookHelper(SplashActivity.this);
            preference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean isFirstRun = preference.getFirstRun();
            Log.d(TAG, "doInBackground: Start");
            if(!isFirstRun){
                ArrayList<Book> books = preLoad();
                helper.open();

                Log.d(TAG, "doInBackground: books : " + books.size());

                for(Book book : books){
                    helper.insert(book);
                    Log.d(TAG, "doInBackground: inserting");
                }

                helper.close();
                Log.d(TAG, "doInBackground: done");
                preference.setFirstRun(false);
            } else{
                try {
                    Log.d(TAG, "doInBackground: loading");
                    synchronized (this) {
                        this.wait(2000);
                    }
                } catch (Exception e) {
                }
            }

            Log.d(TAG, "doInBackground: finish");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public ArrayList<Book> preLoad(){
        ArrayList<Book> books = new ArrayList<Book>();

        for(int i=0; i<names.length; i++){
            Book book = new Book();
            book.setName(names[i]);
            book.setRaw_id(id_raws[i]);

            books.add(book);
        }

        return books;
    }
}
