package com.example.precarga;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.precarga.data.SessionManager;

public class Utils {
    public static void solicitarLogin(Activity activity) {
        Context context = activity.getApplicationContext();

        SessionManager sm = new SessionManager(context);

        sm.saveAuthToken(null);

        context.startActivity(new Intent(context, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        activity.finish();
        activity.setResult(Activity.RESULT_OK);
    }
}
