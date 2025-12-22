package com.example.greenbin.features.feature_profile.presentation

data class ProfileUiState (
    val name: String = "",
    val phone: String = "",
    val address: String = "",
    val isLoading: Boolean = false,
    val showDataSavedMessage: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val error: String? = null
)