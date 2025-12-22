package com.example.greenbin.features.feature_profile.presentation

sealed interface ProfileUiEvent {
    data class NameChanged(val name: String) : ProfileUiEvent
    data class PhoneChanged(val phone: String) : ProfileUiEvent
    data class AddressChanged(val address: String) : ProfileUiEvent
    data object SaveClicked : ProfileUiEvent
    data object LogoutClicked : ProfileUiEvent
    data object ConfirmLogout : ProfileUiEvent
    data object DismissLogoutDialog : ProfileUiEvent
    data object DataSavedMessageShown : ProfileUiEvent
}
