package com.example.greenbin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenbin.features.feature_welcome.presentation.WelcomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToMap = { navController.navigate("map") }
            )
        }

        // временно можно сделать "заглушки", чтобы не было ошибок
        composable("login") { /* TODO: LoginScreen() */ }
        composable("register") { /* TODO: RegisterScreen() */ }
        composable("map") { /* TODO: MapScreen() */ }
    }
}
