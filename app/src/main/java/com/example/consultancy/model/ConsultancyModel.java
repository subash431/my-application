package com.example.consultancy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConsultancyModel implements Serializable {
    private String address;
    private String description;
    private int id;
    private String name;
    private String phoneNumber;
    private String titleDescription;
    //private List<String> countries;

    public ConsultancyModel(){

    }

    public ConsultancyModel(String address, String description, int id, String name, String phoneNumber, String titleDescription) {
        this.address = address;
        this.description = description;
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.titleDescription = titleDescription;
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

    /*public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }*/
}
