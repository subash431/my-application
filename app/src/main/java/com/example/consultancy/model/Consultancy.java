package com.example.consultancy.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Consultancy implements Serializable {
    private String address;
    private String name;
    private String phoneNumber;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titleDescription;
    private String description;

    public Consultancy(){

    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
