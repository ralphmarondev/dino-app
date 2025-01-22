package com.ralphmarondev.dinoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ralphmarondev.dinoapp.navigation.AppNavigation
import com.ralphmarondev.dinoapp.ui.theme.DinoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val preferences = MyApp.preferences
            var isDarkTheme by remember { mutableStateOf(preferences.isDarkTheme()) }

            DinoAppTheme(darkTheme = isDarkTheme) {
                AppNavigation(
                    isDarkTheme = isDarkTheme,
                    toggleDarkTheme = {
                        preferences.toggleDarkTheme()
                        isDarkTheme = !isDarkTheme
                    }
                )
            }
        }
    }
}
