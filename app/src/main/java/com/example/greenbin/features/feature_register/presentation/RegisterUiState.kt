package com.example.greenbin.features.feature_register.presentation

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val agreeToTermsChanged: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
