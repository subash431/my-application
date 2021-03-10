package com.example.consultancy.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Prefs {
    private SharedPreferences pref;
    private Gson gson;
    private static  final String APP_SHARED_PREFS = "MainPrefData";
    private static final String MAIN_PREF_DATA = "main_pref_data";

    public Prefs(Context context){
        this.pref = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public  boolean setPrefs(MainPrefData param){
        return pref.edit().putString(MAIN_PREF_DATA, gson.toJson(param)).commit();
    }

    public MainPrefData getPrefs(){
        String searchParams = pref.getString(MAIN_PREF_DATA, null);
        if (searchParams != null){
            return  gson.fromJson(searchParams, MainPrefData.class);
        }else{
            return null;
        }
    }

    public void clearPrefs(){
        pref.edit().clear().apply();
    }
}
