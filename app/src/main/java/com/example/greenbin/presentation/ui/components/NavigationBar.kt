package com.greenbin.ui.components

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val currentRoute = currentBackStackEntry?.destination?.route.orEmpty()

    // Общие цвета для всех элементов
    val itemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = Color(0xFF1D1D1B),
        selectedTextColor = Color(0xFF00858C),
        indicatorColor = Color(0xFFDEEFF3),
        unselectedIconColor = Color.Gray.copy(alpha = 0.6f),
        unselectedTextColor = Color.Gray.copy(alpha = 0.6f)
    )
    Log.d("BottomBar", "Current route: $currentRoute") // отладочка
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xFFF7FBFC),
        contentColor = Color(0xFF595C5C),
        tonalElevation = 8.dp
    ) {
        // Главная
        NavigationBarItem(
            selected = currentRoute.split(".").last().lowercase() == AppScreen.Main.route || currentRoute.startsWith(AppScreen.Main.route + "/"),
            onClick = {
                navController.navigate(AppScreen.Main) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Главная") },
            label = { Text("Главная", fontSize = 12.sp) },
            alwaysShowLabel = true,   // label только у выбранного
            colors = itemColors
        )

        // Карта
        NavigationBarItem(
            selected = currentRoute.split(".").last().lowercase().split("/")[0] == "map",
            onClick = {
                navController.navigate(AppScreen.Map(categoryId = null)) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Карта") },
            label = { Text("Карта", fontSize = 12.sp) },
            alwaysShowLabel = true,
            colors = itemColors
        )

        // Обучение
        NavigationBarItem(
            selected = currentRoute.split(".").last().lowercase() == AppScreen.Learning.route,
            onClick = {
                navController.navigate(AppScreen.Learning) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Outlined.Bookmark, contentDescription = "Обучение") },
            label = { Text("Обучение", fontSize = 12.sp) },
            alwaysShowLabel = true,
            colors = itemColors
        )

        // Настройки
        NavigationBarItem(
            selected = currentRoute.split(".").last().lowercase() == AppScreen.Settings.route,
            onClick = {
                navController.navigate(AppScreen.Settings) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Настройки") },
            label = { Text("Настройки", fontSize = 12.sp) },
            alwaysShowLabel = true,
            colors = itemColors
        )
    }
}