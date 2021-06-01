package com.cybernerd.bgmiguide.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("deviceToken", Context.MODE_PRIVATE)

    companion object {
        const val DEVICE_TOKEN = ""
    }


    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(DEVICE_TOKEN, token)
        editor.apply()
    }


    fun fetchAuthToken(): String? {
        return prefs.getString(DEVICE_TOKEN, null)
    }
}