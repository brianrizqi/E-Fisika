package com.blue.miqdad.e_fisika.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.blue.miqdad.e_fisika.R;

public class AppPreference {
    SharedPreferences preferences;
    Context context;

    public AppPreference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = preferences.edit();
        String key = context.getResources().getString(R.string.is_first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public Boolean getFirstRun(){
        String key = context.getResources().getString(R.string.is_first_run);
        return preferences.getBoolean(key, true);
    }
}
