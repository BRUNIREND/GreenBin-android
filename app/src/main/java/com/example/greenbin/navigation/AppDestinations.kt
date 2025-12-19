package com.example.greenbin.navigation



import kotlinx.serialization.Serializable

@Serializable
sealed class AppScreen(val route: String) {
    // Auth экраны (без BottomBar)
    @Serializable
    data object Welcome : AppScreen("welcome")
    @Serializable
    data object Login : AppScreen("login")
    @Serializable
    data object Register : AppScreen("register")
    @Serializable
    data object ForgotPassword : AppScreen("forgot_password")
//    composable ("forgot_password"){ Text("Сброс пароля") }
//    composable("map") { Text("Карта") }

    // Основные экраны (с BottomBar)
    @Serializable
    data object Main : AppScreen("main")
    @Serializable
    data object Map : AppScreen("map/{categoryId?}") {
        fun createRoute(categoryId: String? = null) =
            if (categoryId != null) "map/$categoryId" else "map"
    }
    @Serializable
    data object Learning : AppScreen("learning")
    @Serializable
    data object Settings : AppScreen("settings")
}