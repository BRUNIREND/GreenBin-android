package com.example.greenbin.features.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<AuthUiEffect>()
    val uiEffect: Flow<AuthUiEffect> = _uiEffect.receiveAsFlow()

    fun onEvent(event: AuthUiEvent){
        when (event) {
            is AuthUiEvent.EmainChanged -> _uiState.update { it.copy(email = event.email) }
            is AuthUiEvent.PasswordChanged -> _uiState.update { it.copy(password = event.password) }
            is AuthUiEvent.LoginClicked -> login()
        }
    }
    private fun login(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            // TODO: UseCase
            _uiState.update { it.copy(isLoading = false) }
            _uiEffect.send(AuthUiEffect.Navigate.ToMain)
        }
    }
}

sealed class AuthUiEffect {
    sealed class Navigate : AuthUiEffect() {
        data object ToForgotPassword : Navigate()
        data object ToRegister : Navigate()
        data object ToMain: Navigate()
    }
}