package com.example.somesh.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.somesh.Login.Login;
import com.example.somesh.myapplication_test.Home;

import java.util.HashMap;

/**
 * Created by Somesh on 1/2/2018.
 */

public class sessionMaker {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREFER_NAME = "SessionList";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    public sessionMaker(Context context){

        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createUserLoginSession(String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    public int checkLogin(){
        // Check login status
        if(this.isUserLoggedIn()){

            // user is logged in redirect him to Login Activity
            Intent i = new Intent(_context, Home.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return 1;
        }
        return 0;
    }

    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, Login.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


    // Check for login
    public boolean isUserLoggedIn(){

        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
