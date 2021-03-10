package com.example.consultancy.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class UIUtils {

    public static void closeSoftKeyboard(Activity activity, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    public static MaterialDialog runProgressDialog(Context context, String title, String body) {
        final MaterialDialog progressDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(body)
                .progress(true, 0)
                .show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    public static MaterialDialog runProgressDialogWithoutTitle(Context context, String body) {
        final MaterialDialog progressDialog = new MaterialDialog.Builder(context)
                .content(body)
                .progress(true, 0)
                .show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }


}
