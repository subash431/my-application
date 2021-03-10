package com.example.consultancy.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.consultancy.model.Consultancy;

@Database(entities = {Consultancy.class}, version = 3, exportSchema = false)
public  abstract class AppDatabase extends RoomDatabase {

    public abstract ConsultancyDao consultancyDao();

    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();
    private static final String db_name = "DB_CONSULTANCY";

    public static AppDatabase getInstance(Context context){
        synchronized (sLock){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.db_name)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }


}
