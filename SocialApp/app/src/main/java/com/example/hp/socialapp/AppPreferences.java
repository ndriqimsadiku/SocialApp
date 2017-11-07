package com.example.hp.socialapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hp on 11/4/2017.
 */

public class AppPreferences {


        private static String PREFERENCES_KEY = "preferences";
        private static String USER_ID_KEY = "user_id";

        public static SharedPreferences sharedPreferences;

        public static void init(Context context){
            sharedPreferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        }
        public static void saveUserId(String id){
            sharedPreferences.edit().putString(USER_ID_KEY,id).commit();
        }
        public static String getUserId(){
            return sharedPreferences.getString(USER_ID_KEY,"");
        }

}
