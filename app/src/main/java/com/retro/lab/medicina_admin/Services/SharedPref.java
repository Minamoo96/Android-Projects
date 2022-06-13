package com.retro.lab.medicina_admin.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.retro.lab.medicina_admin.LoginActivity;
import com.retro.lab.medicina_admin.MainActivity;
import com.retro.lab.medicina_admin.Models.AdminModel;

public class SharedPref {

    private static SharedPref mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref";

    private static final String KEY_ID = "UID";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_USERPASSWORD = "Password";

    private SharedPref(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPref(context);
        }
        return mInstance;
    }

    public void userSignup(AdminModel admin){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, admin.getId());
        editor.putString(KEY_USERNAME, admin.getUsername());
        editor.putString(KEY_USERPASSWORD, admin.getPassword());
        editor.apply();

    }

    public Object getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new AdminModel(
                sharedPreferences.getInt(KEY_ID, 0),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_USERPASSWORD, null)
        );
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USERNAME, null) != null){
            return true;
        }
        return false;
    }

    public void logOut(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}