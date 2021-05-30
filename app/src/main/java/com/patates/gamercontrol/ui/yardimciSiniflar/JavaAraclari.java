package com.patates.gamercontrol.ui.yardimciSiniflar;

import android.app.Activity;
import android.content.Context;

import android.view.inputmethod.InputMethodManager;



import static android.content.Context.INPUT_METHOD_SERVICE;

public class JavaAraclari {
    public static void klavyeKapat(Context context, Activity activity){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
