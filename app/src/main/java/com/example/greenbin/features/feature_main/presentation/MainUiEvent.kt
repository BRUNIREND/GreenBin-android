package com.example.greenbin.features.feature_main.presentation


sealed class MainUiEvent {
    data class CategoryClicked(val categoryId: String) : MainUiEvent()
    data object  ProfileClicked : MainUiEvent()
    data object MapClicked : MainUiEvent()
    data object LessonsClicked : MainUiEvent() // Уроки сделаем позже
}


