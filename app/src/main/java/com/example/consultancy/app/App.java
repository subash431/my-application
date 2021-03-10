package com.example.consultancy.app;

import android.app.Application;

import com.example.consultancy.db.AppDatabase;
import com.example.consultancy.helper.ConsultancyList;
import com.example.consultancy.model.Consultancy;
import com.example.consultancy.pref.MainPrefData;
import com.example.consultancy.pref.Prefs;

import java.util.ArrayList;

public class App extends Application {
    public static Prefs prefs;
    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        initVars();
        initDB();
    }

    private void initDB(){
        db = AppDatabase.getInstance(getApplicationContext());

        if (db.consultancyDao().getAllConsultancy() != null){
            if (db.consultancyDao().getAllConsultancy().size() < 1){
                ArrayList<Consultancy> consultancyArrayList = ConsultancyList.produce();

                db.consultancyDao().insertAll(consultancyArrayList);
            }
        }
    }

    private void initVars() {
        prefs = new Prefs(this);

        if (App.prefs.getPrefs() == null) {

            App.prefs.setPrefs(new MainPrefData());
        }
    }
}
