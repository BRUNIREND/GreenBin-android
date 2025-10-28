package com.example.greenbin.features.feature_welcome.presentation

sealed interface WelcomeUiEvent {
    data object LoginClicked : WelcomeUiEvent
    data object RegisterClicked : WelcomeUiEvent
    data object MapClicked : WelcomeUiEvent
}