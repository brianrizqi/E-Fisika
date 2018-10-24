package com.blue.miqdad.e_fisika.DB;

import android.os.AsyncTask;

import com.blue.miqdad.e_fisika.Models.AppPreference;
import com.blue.miqdad.e_fisika.R;

public class DatabaseGenerator {
    public static final String[] names = {"Buku satu"};
    public static final long[] id_raws = {R.raw.a_room_with_a_view_morrison};

    public static class LoadDatabaseAsyncTask extends AsyncTask<Void, Void, Void>{

        BookHelper helper;
        AppPreference preference;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
