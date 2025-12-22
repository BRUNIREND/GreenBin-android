package com.example.greenbin.features.feature_profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.model.User
import com.example.domain.auth.usecase.GetUserProfileUseCase
import com.example.domain.auth.usecase.LogoutUseCase
import com.example.domain.auth.usecase.SaveUserProfileUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<ProfileUiEffect>()
    val uiEffect: Flow<ProfileUiEffect> = _uiEffect.receiveAsFlow()

    init {
        loadProfile()
    }

    fun onEvent(event: ProfileUiEvent) {
        when (event) {
            is ProfileUiEvent.NameChanged -> _uiState.update { it.copy(name = event.name) }
            is ProfileUiEvent.PhoneChanged -> _uiState.update { it.copy(phone = event.phone) }
            is ProfileUiEvent.AddressChanged -> _uiState.update { it.copy(address = event.address) }
            is ProfileUiEvent.SaveClicked -> saveProfile()
            is ProfileUiEvent.LogoutClicked -> _uiState.update { it.copy(showLogoutDialog = true) }
            is ProfileUiEvent.ConfirmLogout -> logout()
            is ProfileUiEvent.DismissLogoutDialog -> _uiState.update { it.copy(showLogoutDialog = false) }
            is ProfileUiEvent.DataSavedMessageShown -> _uiState.update { it.copy(showDataSavedMessage = false) }
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val user = withContext(Dispatchers.IO) { getUserProfileUseCase(uid) }
            user?.let {
                _uiState.update {
                    it.copy(
                        name = it.name ?: "",
                        phone = it.phone ?: "",
                        address = it.address ?: "",
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun saveProfile() {
        viewModelScope.launch {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val user = User(
                uid = uid,
                email = FirebaseAuth.getInstance().currentUser?.email,
                displayName = _uiState.value.name.takeIf { it.isNotBlank() },
                phone = _uiState.value.phone.takeIf { it.isNotBlank() },
                address = _uiState.value.address.takeIf { it.isNotBlank() }
            )

            val result = withContext(Dispatchers.IO) { saveUserProfileUseCase(user) }
            result.onSuccess {
                _uiState.update { it.copy(showDataSavedMessage = true) }
            }.onFailure {
                _uiState.update { it.copy(error = "Error saving profile") }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { logoutUseCase() }
            result.onSuccess {
                _uiEffect.send(ProfileUiEffect.Navigate.ToWelcome)
            }
        }
    }
}

sealed class ProfileUiEffect {
    sealed class Navigate : ProfileUiEffect() {
        data object ToWelcome : Navigate()
    }
}