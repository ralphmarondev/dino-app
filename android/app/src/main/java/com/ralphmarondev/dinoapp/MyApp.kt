package com.ralphmarondev.dinoapp

import android.app.Application
import com.ralphmarondev.dinoapp.data.preferences.AppPreferences

class MyApp : Application() {

    companion object {
        lateinit var preferences: AppPreferences
            private set
    }

    override fun onCreate() {
        super.onCreate()

        preferences = AppPreferences(applicationContext)
    }
}