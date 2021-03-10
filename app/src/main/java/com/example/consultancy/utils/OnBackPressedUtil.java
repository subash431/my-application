package com.example.consultancy.utils;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OnBackPressedUtil {
    private Context context;

    public OnBackPressedUtil(Context context){
        this.context = context;
    }

    public void onBackPress(int backPressed){
        if (backPressed == 1) {
            Toast.makeText(context, "Press again to exit", Toast.LENGTH_LONG).show();
        } else {
            Intent exitIntent = new Intent(Intent.ACTION_MAIN);
            exitIntent.addCategory(Intent.CATEGORY_HOME);
            exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(exitIntent);
        }
    }

}
