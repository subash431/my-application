package com.example.consultancy.pref;

public class MainPrefData {
    private String email;
    private boolean isLoggedIn = false;
    private String userId;
    private String password;

    private boolean isRemember = false;

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
