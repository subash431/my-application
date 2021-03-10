package com.example.consultancy.model;

public class FormData {

    private String firstName;
    private String lastName;
    private String dOB;
    private String gender;
    private String email;
    private String nationality;
    private String universityName;

    public FormData(String firstName, String lastName, String dOB, String gender, String email, String nationality, String universityName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dOB = dOB;
        this.gender = gender;
        this.email = email;
        this.nationality = nationality;
        this.universityName = universityName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

}
