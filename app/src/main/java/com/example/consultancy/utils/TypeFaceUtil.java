package com.example.consultancy.utils;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.TextView;

import com.example.consultancy.R;

public class TypeFaceUtil {

    public static void monsterrat_light(Context context, TextView tv) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.montserrat_light);
        tv.setTypeface(typeface);
    }

    public static void raleway_medium(Context context, TextView tv) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.raleway_medium);
        tv.setTypeface(typeface);
    }

    public static void nunito_light(Context context, TextView tv) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.nunito_light);
        tv.setTypeface(typeface);
    }

    public static void nunito_regular(Context context, TextView tv) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.nunito_regular);
        tv.setTypeface(typeface);
    }

    public static void nunito_bold(Context context, TextView tv) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.nunito_bold);
        tv.setTypeface(typeface);
    }


}
