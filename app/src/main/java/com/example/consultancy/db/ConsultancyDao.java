package com.example.consultancy.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.consultancy.model.Consultancy;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ConsultancyDao {

    @Insert
    void insertAll(ArrayList<Consultancy> consultancies);

    @Query("Select * from consultancy")
    List<Consultancy> getAllConsultancy();

    @Query("Select * from Consultancy Where id = :id LIMIT 1")
    Consultancy getConsultancyById(int id);


}
