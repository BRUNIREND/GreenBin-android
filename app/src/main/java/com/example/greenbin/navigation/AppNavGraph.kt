package com.example.greenbin.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenbin.features.feature_welcome.presentation.WelcomeScreen

//@Composable
//fun AppNavGraph(
//    navController: NavHostController
//) {
//    NavHost(
//        navController = navController,
//        startDestination = "welcome"
//    ) {
//        composable("welcome") {
//            WelcomeScreen(
//                onNavigateToLogin = { navController.navigate("login") },
//                onNavigateToRegister = { navController.navigate("register") },
//                onNavigateToMap = { navController.navigate("map") }
//            )
//        }
//
//        // временно можно сделать "заглушки", чтобы не было ошибок
//        composable("login") { /* TODO: LoginScreen() */ }
//        composable("register") { /* TODO: RegisterScreen() */ }
//        composable("map") { /* TODO: MapScreen() */ }
//    }
//}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(
            navController = navController
        ) }
        composable("login") { Text("Экран логина") }
        composable("register") { Text("Экран регистрации") }
        composable("map") { Text("Карта") }
    }
}
