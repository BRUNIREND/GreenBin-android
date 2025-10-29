package com.example.greenbin.features.feature_auth.presentation

sealed interface AuthUiEvent{
    data class EmainChanged(val email: String) : AuthUiEvent
    data class PasswordChanged(val password: String) : AuthUiEvent
    data object LoginClicked : AuthUiEvent
}