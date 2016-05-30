package com.sheeloh.alhusban.sheelohapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hashim Al-husban
 * on 5/27/2016.
 */
public class SharedUtil {
    public static String getShared(Context context, String name, String defValue) {
        SharedPreferences data = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return data.getString(name, defValue);

    }

    public static void setShared(Context context, String name, String value) {
        SharedPreferences data = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = data.edit();
        edit.putString(name, value);
        edit.commit();
    }
}
