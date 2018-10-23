package com.blue.miqdad.e_fisika.DB;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_BOOK = "book";

    public static final class BookColumns implements BaseColumns {
        public static String NAMA = "nama";
        public static String RAW_ID = "raw_id";
    }
}
