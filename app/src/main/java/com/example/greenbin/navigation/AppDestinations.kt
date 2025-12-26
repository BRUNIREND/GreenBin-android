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


    // Основные экраны (с BottomBar)
    @Serializable
    data object Main : AppScreen("main")

    @Serializable
    data class Map(val categoryId: String? = null) : AppScreen("map/{categoryId?}") {
        fun createRoute() = if (categoryId != null) "map/$categoryId" else "map"
    }
//    @Serializable
//    data object Map : AppScreen("map/{categoryId?}") {
//        fun createRoute(categoryId: String? = null) =
//            if (categoryId != null) "map/$categoryId" else "map"
//    }
    @Serializable
    data object Learning : AppScreen("learning")

    @Serializable
    data class LearningTopic(val topicId: String) : AppScreen("learning_topic/{topicId}") {
        fun createRoute(topicId: String) = "learning_topic/$topicId"
    }

    @Serializable
    data class LearningTest(val topicId: String) : AppScreen("learning_test/{topicId}") {
        fun createRoute(topicId: String) = "learning_test/$topicId"
    }
    @Serializable
    data object Settings : AppScreen("settings")

    @Serializable
    data object PointDetail : AppScreen("point_detail/{pointId}") {
        fun createRoute(pointId: String) = "point_detail/$pointId"
    }
}