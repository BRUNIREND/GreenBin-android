package com.example.greenbin.features.feature_register.presentation

sealed interface RegisterUiEvent {
    data class EmailChanged(val email: String) : RegisterUiEvent
    data class NameChanged(val name: String) : RegisterUiEvent
    data class PasswordChanged(val password: String): RegisterUiEvent
    data class ConfirmPasswordChanged(val confirmPassword: String): RegisterUiEvent
    data class AgreeToTermsChanged(val agreed: Boolean): RegisterUiEvent
    data object RegisterClicked : RegisterUiEvent
    data object LoginClicked : RegisterUiEvent
}