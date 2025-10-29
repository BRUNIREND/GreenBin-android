package com.example.greenbin.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenbin.features.feature_auth.presentation.AuthentificationScreen
import com.example.greenbin.features.feature_welcome.presentation.WelcomeScreen
import com.google.api.Authentication


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(
            navController = navController
        ) }
        composable("login") {
            AuthentificationScreen(
                navController = navController
            )
        }
        composable("register") { Text("Экран регистрации") }
        composable("map") { Text("Карта") }
    }
}
