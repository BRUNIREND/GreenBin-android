package com.example.greenbin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.greenbin.features.feature_auth.presentation.AuthentificationScreen
import com.example.greenbin.features.feature_learning.LearningMainScreen
import com.example.greenbin.features.feature_learning.LearningTestScreen
import com.example.greenbin.features.feature_learning.LearningTopicScreen
import com.example.greenbin.features.feature_map.MapScreen
import com.example.greenbin.features.feature_map.PointDetailScreen
import com.example.greenbin.features.feature_profile.presentation.ProfileScreen
import com.example.greenbin.features.feature_register.presentation.RegistrationScreen
import com.example.greenbin.features.feature_welcome.presentation.WelcomeScreen
import com.greenbin.features.feature_main.presentation.MainScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Welcome,
        modifier = modifier
    ) {
        // === Auth экраны ===
        composable<AppScreen.Welcome> {
            WelcomeScreen(navController = navController)
        }
        composable<AppScreen.Login> {
            AuthentificationScreen(navController = navController)
        }
        composable<AppScreen.Register> {
            RegistrationScreen(navController = navController)
        }

        // === Основные экраны ===
        composable<AppScreen.Main> {
            MainScreen(navController = navController)
        }

        // Глобальная карта
        composable("map") {
            MapScreen(categoryId = null, navController = navController)
        }

        // Карта по категории
        composable<AppScreen.Map> {backStackEntry ->
            val args = backStackEntry.toRoute<AppScreen.Map>()
            MapScreen(
                categoryId = args.categoryId,
                navController = navController
            )
        }
//        composable<AppScreen.Map> { backStackEntry ->
//            val categoryId = backStackEntry.arguments?.getString("categoryId")
//            MapScreen(categoryId = categoryId, navController = navController)
//        }

        composable<AppScreen.Learning> {
            LearningMainScreen(navController = navController)
        }

        composable<AppScreen.LearningTopic> { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: return@composable
            LearningTopicScreen(topicId = topicId, navController = navController)
        }

        composable<AppScreen.LearningTest> { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: return@composable
            LearningTestScreen(topicId = topicId, navController = navController)
        }

        composable<AppScreen.Settings> {
            ProfileScreen(navController = navController)
        }

        // ←←←←← Детальный экран пункта — В КОНЕЦ!
        composable(
            route = "point_detail/{pointId}",
            arguments = listOf(navArgument("pointId") { type = NavType.StringType })
        ) { backStackEntry ->
            val pointId = backStackEntry.arguments?.getString("pointId")
                ?: throw IllegalArgumentException("pointId required")

            PointDetailScreen(
                pointId = pointId,
                navController = navController
            )
        }
    }
}