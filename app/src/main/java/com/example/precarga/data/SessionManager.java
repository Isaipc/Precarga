package com.example.precarga.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.precarga.R;

public class SessionManager {

    private Context context;
    private SharedPreferences prefs;

    private final String USER_TOKEN = "user_token";

    public SessionManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token){
        prefs.edit().putString(USER_TOKEN, token).apply();
    }

    public String fetchAuthToken(){
        return prefs.getString(USER_TOKEN, null);
    }


}
