package com.blue.miqdad.e_fisika.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private int id;
    private String name;
    private long raw_id;

    public Book(){

    }

    protected Book(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.raw_id = in.readLong();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeLong(this.raw_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRaw_id() {
        return raw_id;
    }

    public void setRaw_id(long raw_id) {
        this.raw_id = raw_id;
    }
}
