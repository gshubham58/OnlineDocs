package com.shubham.onlinedocs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import java.util.Set;


/**
 * Created by hp on 18-May-17.
 */

public class SharedPref {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
public  static SharedPref getSharedPref(Context cont){
    return new SharedPref(cont);
}

    public SharedPref(Context context) {
        String prefFile=context.getPackageName();
        sharedPreferences = context.getSharedPreferences(prefFile, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void delete(String key) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit();
        }
    }
    public void setvalue(String key, Object value) {
        delete(key);
        if (value instanceof String) {
            editor.putString(key, (String) value);
        }else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        }else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        }else if (value !=null) {
          throw new RuntimeException("Trying to Save Invalid Preference");
        }
        editor.commit();
    }

    public <T> T getvalue(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }


}
