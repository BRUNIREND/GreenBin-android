package com.example.greenbin.presentation.ui.screens.auth

sealed class SignUpEvent {
    data class EmailChanged(val value: String) : SignUpEvent()
    data class PasswordChanged(val value: String) : SignUpEvent()
    data class ConfirmPasswordChanged(val value: String) : SignUpEvent()
    object Submit : SignUpEvent()
}