package com.example.greenbin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenbin.features.feature_auth.presentation.AuthentificationScreen
import com.example.greenbin.features.feature_register.presentation.RegistrationScreen
import com.example.greenbin.features.feature_welcome.presentation.WelcomeScreen
import com.greenbin.features.feature_main.presentation.MainScreen

@Composable
fun AppNavGraph(navController: NavHostController,
                modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Welcome,
        modifier = modifier
    ) {
        // === Экраны БЕЗ BottomBar (Auth) ===
        composable<AppScreen.Welcome> {
            WelcomeScreen(navController = navController)
        }
        composable<AppScreen.Login> {
            AuthentificationScreen(navController = navController)
        }
        composable<AppScreen.Register> {
            RegistrationScreen(navController = navController)
        }

        // === Основные экраны С BottomBar ===
        composable<AppScreen.Main> {
            MainScreen(navController = navController)
        }
        composable<AppScreen.Map> { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            // MapScreen(categoryId, navController)   // ← потом сделаешь
            androidx.compose.material3.Text("Карта: $categoryId")
        }
        composable<AppScreen.Learning> {
            androidx.compose.material3.Text("Обучение")
        }
        composable<AppScreen.Settings> {
            androidx.compose.material3.Text("Настройки")
        }
    }
}