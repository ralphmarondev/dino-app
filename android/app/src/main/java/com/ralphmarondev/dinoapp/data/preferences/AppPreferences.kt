package com.ralphmarondev.dinoapp.data.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFERENCES_NAME = "dino_app_pref"
        private const val DARK_MODE = "dark_mode"
    }

    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(DARK_MODE, false)
    }

    fun toggleDarkTheme() {
        sharedPreferences.edit().putBoolean(DARK_MODE, !isDarkTheme()).apply()
    }
}