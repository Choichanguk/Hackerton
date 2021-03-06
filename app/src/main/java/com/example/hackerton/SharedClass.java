package com.example.hackerton;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 쉐어드의 모든 save, load 관련 메소드 들어있는 class
 */
public class SharedClass {
    private static final String PREFERENCES_NAME = "유저 로그인 정보";

    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    public static void saveUserId(Context context, String ID){
        SharedPreferences sharedPreferences = getPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", ID);
        editor.apply();
    }

    public static void saveLoginStatus(Context context, boolean islogin){
        SharedPreferences sharedPreferences = getPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_login", islogin);
        editor.apply();
    }

    public static String loadUserId(Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        String user_id = sharedPreferences.getString("user_id", null);

        if(user_id == null){
            user_id = "";
        }
        return user_id;
    }

    public static boolean loadLoginStatus(Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        Boolean is_login = sharedPreferences.getBoolean("is_login", false);
        return is_login;
    }
}
