package com.example.greenbin.features.feature_welcome.presentation

sealed class WelcomeUiEvent{
    object NavigateToLogin : WelcomeUiEvent()
    object NavigateToRegister : WelcomeUiEvent()
    object NavigateToMap : WelcomeUiEvent()
}