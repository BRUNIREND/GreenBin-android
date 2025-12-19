package com.greenbin.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.greenbin.navigation.AppScreen

@Composable
fun AppBottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar(modifier = modifier) {
        // Главная
        NavigationBarItem(
            selected = currentRoute == AppScreen.Main.route,
            onClick = {
                navController.navigate(AppScreen.Main) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Главная") },
            label = { Text("Главная") }
        )

        // Карта (глобальная — без категории)
        NavigationBarItem(
            selected = currentRoute?.startsWith("map") == true,
            onClick = {
                navController.navigate(AppScreen.Map.createRoute()) {  // ← без параметра
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Карта") },
            label = { Text("Карта") }
        )

        // Обучение
        NavigationBarItem(
            selected = currentRoute == AppScreen.Learning.route,
            onClick = {
                navController.navigate(AppScreen.Learning) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Outlined.Bookmark, contentDescription = "Обучение") },
            label = { Text("Обучение") }
        )

        // Настройки
        NavigationBarItem(
            selected = currentRoute == AppScreen.Settings.route,
            onClick = {
                navController.navigate(AppScreen.Settings) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Настройки") },
            label = { Text("Настройки") }
        )
    }
}