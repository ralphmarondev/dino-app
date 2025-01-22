package com.ralphmarondev.dinoapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.dinoapp.presentation.screens.home.HomeScreen

@Composable
fun AppNavigation(
    isDarkTheme: Boolean,
    toggleDarkTheme: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                isDarkTheme = isDarkTheme,
                toggleDarkTheme = toggleDarkTheme,
                navigateToFavoriteDinosaurs = {
                    navController.navigate(Routes.Favorites) {
                        launchSingleTop = true
                    }
                },
                navigateToNewDinosaur = {
                    navController.navigate(Routes.NewDinosaur) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Routes.Favorites> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Favorites",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.W500
                )
            }
        }

        composable<Routes.NewDinosaur> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "New Dinosaur",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }
}