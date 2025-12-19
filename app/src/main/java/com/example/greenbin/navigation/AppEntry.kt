package com.example.greenbin.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.greenbin.ui.components.AppBottomNavigationBar

@Composable
fun AppEntry() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    // Показываем BottomBar только на основных экранах

    val showBottomBar = currentBackStackEntry?.destination?.route?.let { route ->
        route == AppScreen.Main.route ||
                route.startsWith("map") ||
                route == AppScreen.Learning.route ||
                route == AppScreen.Settings.route
    } ?: false

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                AppBottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}