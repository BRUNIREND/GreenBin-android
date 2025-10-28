package com.example.greenbin.features.feature_welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.usecase.CheckAuthUseCase
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


//region myregion
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val checkAuthUseCase: CheckAuthUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WelcomeUiState(isLoading = true))
    val uiState: StateFlow<WelcomeUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<WelcomeUiEffect>()
    val uiEffect: Flow<WelcomeUiEffect> = _uiEffect.receiveAsFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            try {
                val isLoggedIn = checkAuthUseCase()
                if (isLoggedIn) {
                    _uiEffect.send(WelcomeUiEffect.Navigate.ToMain)
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка проверки авторизации") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
    fun onEvent(event: WelcomeUiEvent){
        when (event) {
            WelcomeUiEvent.LoginClicked -> navigateTo(WelcomeUiEffect.Navigate.ToLogin)
            WelcomeUiEvent.RegisterClicked -> navigateTo(WelcomeUiEffect.Navigate.ToRegister)
            WelcomeUiEvent.MapClicked -> navigateTo(WelcomeUiEffect.Navigate.ToMap)
        }
    }
    private fun navigateTo(nav: WelcomeUiEffect.Navigate) {
        viewModelScope.launch {
            _uiEffect.send(nav)
        }
    }
//    private val _events = MutableSharedFlow<WelcomeUiEvent>()
//    val events = _events.asSharedFlow()
//
//    fun onLoginClick() {
//        viewModelScope.launch { _events.emit(WelcomeUiEvent.NavigateToLogin)}
//    }
//
//    fun onRegisterClick() {
//        viewModelScope.launch { _events.emit(WelcomeUiEvent.NavigateToRegister)}
//    }
//
//    fun onMapClick() {
//        viewModelScope.launch { _events.emit(WelcomeUiEvent.NavigateToMap) }
//    }
}
//endregion

// Одноразовые эффекты (навигация, toast, etc.)
sealed class WelcomeUiEffect {
    sealed class Navigate : WelcomeUiEffect() {
        data object ToLogin : Navigate()
        data object ToRegister : Navigate()
        data object ToMap : Navigate()
        data object ToMain : Navigate()
    }
}