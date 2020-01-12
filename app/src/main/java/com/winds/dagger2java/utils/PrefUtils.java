package com.winds.dagger2java.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.winds.dagger2java.App.getSharedPreference;


@Singleton
public class PrefUtils {

    private SharedPreferences prefs;
    private static final int DEFAULT_NUMERIC_VALUE = -1;
    private static final String USER_NAME ="username";

    private final Gson mGson;

    @Inject
    PrefUtils(Gson gson) {
        mGson = gson;
        prefs = getSharedPreference();
    }

    public void setUserName(String userName) {
        Editor editor = prefs.edit();
        editor.putString(USER_NAME, userName);
        editor.apply();
    }

    public String getUserName() {
        return prefs.getString(USER_NAME, "N.A");
    }

    public void setBooleanCommit(String key, boolean value) {
        Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public void setInteger(String key, int value) {
        Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInteger(String key) {
        return prefs.getInt(key, DEFAULT_NUMERIC_VALUE);
    }

    public void setFloat(String key, float value) {
        Editor editor = prefs.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key) {
        return prefs.getFloat(key, (float) DEFAULT_NUMERIC_VALUE);
    }

    /**
     * to set pojo object in preferences. will store json string of it.
     */
    public void setObject(String key, @NonNull Object value) {
        //  prefs = mContext.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString(key, mGson.toJson(value));
        editor.apply();
    }

    /**
     * to get pojo object from json stored in preferences.
     * returns null if key doesn't exist in preferences
     */

    public <T> T getObject(String key, Class<T> pojoClass) {
        String jsonString = prefs.getString(key, null);
        if (jsonString == null) {
            return null;
        }
        return mGson.fromJson(jsonString, pojoClass);
    }

    /**
     * fetches all key-values pairs from preferences in the form of map
     */
    public Map<String, ?> getAll() {
        return prefs.getAll();
    }

    public void removeKey(String key) {
        Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }


    public void clearAll() {
        Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }


}
