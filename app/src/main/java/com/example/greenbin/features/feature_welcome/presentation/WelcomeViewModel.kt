package com.example.greenbin.features.feature_welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//region myregion
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    // можно добавить usecase: CheckAuthStateUseCase
) : ViewModel() {
    private val _events = MutableSharedFlow<WelcomeUiEvent>()
    val events = _events.asSharedFlow()

    fun onLoginClick() {
        viewModelScope.launch { _events.emit(WelcomeUiEvent.NavigateToLogin)}
    }

    fun onRegisterClick() {
        viewModelScope.launch { _events.emit(WelcomeUiEvent.NavigateToRegister)}
    }

    fun onMapClick() {
        viewModelScope.launch { _events.emit(WelcomeUiEvent.NavigateToMap) }
    }
}
//endregion