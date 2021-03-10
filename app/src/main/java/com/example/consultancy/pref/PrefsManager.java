package com.example.consultancy.pref;

import com.example.consultancy.app.App;

public class PrefsManager {
    public static void setEmail(String email){
        MainPrefData mainPrefData = App.prefs.getPrefs();
        mainPrefData.setEmail(email);
        App.prefs.setPrefs(mainPrefData);
    }

    public static void setLoggedIn(Boolean loggedIn){
        MainPrefData mainPrefData = App.prefs.getPrefs();
        mainPrefData.setLoggedIn(loggedIn);
        App.prefs.setPrefs(mainPrefData);
    }

    public static  void setUserid(String userid){
        MainPrefData mainPrefData = App.prefs.getPrefs();
        mainPrefData.setUserId(userid);
        App.prefs.setPrefs(mainPrefData);
    }

    public static  void setRememberMe(Boolean rememberMe){
        MainPrefData mainPrefData = App.prefs.getPrefs();
        mainPrefData.setRemember(rememberMe);
        App.prefs.setPrefs(mainPrefData);
    }

    public static  void setPassword(String password){
        MainPrefData mainPrefData = App.prefs.getPrefs();
        mainPrefData.setPassword(password);
        App.prefs.setPrefs(mainPrefData);
    }

    public static String getEmail(){
        return App.prefs.getPrefs().getEmail();
    }

    public static Boolean getLoggedIn(){
        return App.prefs.getPrefs().isLoggedIn();
    }

    public static void clearPrefs(){
        MainPrefData mainPrefData = App.prefs.getPrefs();
        mainPrefData.setLoggedIn(false);
        App.prefs.setPrefs(mainPrefData);
    }

    public static String getUserId(){
        return App.prefs.getPrefs().getUserId();
    }

    public static  Boolean getRememberMe(){
        return App.prefs.getPrefs().isRemember();
    }

    public static String getPassword(){
        return App.prefs.getPrefs().getPassword();
    }
}
