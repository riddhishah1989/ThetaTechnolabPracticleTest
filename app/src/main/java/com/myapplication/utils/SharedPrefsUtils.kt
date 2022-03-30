package com.myapplication.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsUtils {

    private lateinit var prefs: SharedPreferences

    private const val PREFS_NAME = "SHARED_PREFS"

    const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
    const val USER_EMAIL_ID = "USER_EMAIL_ID"
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getString(key: String): String? {
        return prefs.getString(key, "")
    }

    fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun setString(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()

    }

    fun setBoolean(key: String, value: Boolean) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()

    }

    fun clearData(){
        setBoolean(IS_USER_LOGGED_IN,false)
        setString(USER_EMAIL_ID,"")
    }

}