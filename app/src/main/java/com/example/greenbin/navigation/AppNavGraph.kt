package com.example.greenbin.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenbin.features.feature_auth.presentation.AuthentificationScreen
import com.example.greenbin.features.feature_register.presentation.RegistrationScreen
import com.example.greenbin.features.feature_welcome.presentation.WelcomeScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(
            navController = navController
        ) }
        composable("main") { Text("Главный экран после авторизации") }
        composable("login") {
            AuthentificationScreen(
                navController = navController
            )
        }
        composable("register") {
            RegistrationScreen(navController)
        }
        composable ("forgot_password"){ Text("Сброс пароля") }
        composable("map") { Text("Карта") }
    }
}
