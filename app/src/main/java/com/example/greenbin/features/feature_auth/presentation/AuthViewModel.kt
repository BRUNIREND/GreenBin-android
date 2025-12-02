package com.example.greenbin.features.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.usecase.LoginUseCase
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
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<AuthUiEffect>()
    val uiEffect: Flow<AuthUiEffect> = _uiEffect.receiveAsFlow()

    fun onEvent(event: AuthUiEvent){
        when (event) {
            is AuthUiEvent.EmailChanged -> _uiState.update { it.copy(email = event.email) }
            is AuthUiEvent.PasswordChanged -> _uiState.update { it.copy(password = event.password) }
            is AuthUiEvent.RegisterClicked -> viewModelScope.launch { _uiEffect.send(AuthUiEffect.Navigate.ToRegister) }
            is AuthUiEvent.ForgotPasswordClicked -> viewModelScope.launch { _uiEffect.send(
                AuthUiEffect.Navigate.ToForgotPassword) }
            is AuthUiEvent.LoginClicked -> login()
        }
    }
    private fun login(){
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(error = "Заполните поля") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = loginUseCase(_uiState.value.email, _uiState.value.password)
            result.onSuccess {
                _uiEffect.send(AuthUiEffect.Navigate.ToMain)
            }.onFailure { exception ->
                _uiState.update { it.copy(error = exception.message) }
            }
            _uiState.update { it.copy(isLoading = false) }
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