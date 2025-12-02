package com.example.greenbin.features.feature_register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.usecase.RegisterUseCase
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
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<RegisterUiEffect>()
    val uiEffect: Flow<RegisterUiEffect> = _uiEffect.receiveAsFlow()

    fun onEvent(event: RegisterUiEvent){
        when (event){
            is RegisterUiEvent.NameChanged -> _uiState.update { it.copy(name = event.name) }
            is RegisterUiEvent.EmailChanged -> _uiState.update { it.copy(email = event.email) }
            is RegisterUiEvent.PasswordChanged -> _uiState.update { it.copy(password = event.password) }
            is RegisterUiEvent.ConfirmPasswordChanged -> _uiState.update { it.copy(confirmPassword = event.confirmPassword) }
            is RegisterUiEvent.AgreeToTermsChanged -> _uiState.update { it.copy(agreeToTermsChanged = event.agreed) }
            is RegisterUiEvent.RegisterClicked -> register()
            is RegisterUiEvent.LoginClicked -> viewModelScope.launch { _uiEffect.send(RegisterUiEffect.Navigate.ToLogin) }
        }
    }

    private fun register() {
        val state = _uiState.value
        if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(error = "Пароли не совпадают") }
            return
        }
        if (state.password.length < 8) {
            _uiState.update { it.copy(error = "Пароль слишком короткий (минимум 8 символов)") }
        }
        if (state.password == state.password.lowercase()){
            _uiState.update { it.copy(error = "Пароль должен содержать хотя бы одну букву в верхнем регистре") }
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = withContext(Dispatchers.IO){
                registerUseCase(state.email, state.password)
            }

            result.onSuccess { user ->
                try {
                    FirebaseAuth.getInstance().currentUser!!.sendEmailVerification().await()
                    _uiEffect.send(RegisterUiEffect.Navigate.ToMain)
                    _uiEffect.send(
                        RegisterUiEffect.ShowMessageAndNavigate(
                            message = "Письмо для подтверждения отправлено на ${state.email}",
                            navigateTo = RegisterUiEffect.Navigate.ToMain
                        )
                    )
                } catch (e: Exception) {
                    _uiEffect.send(
                        RegisterUiEffect.ShowMessageAndNavigate(
                            message = "Ошибка отправки письма для подтверждения, но мы вас пропустим",
                            navigateTo = RegisterUiEffect.Navigate.ToMain
                        )
                    )
                }

            }.onFailure { exception ->
                _uiState.update { it.copy(error = exception.message ?: "Ошибка регистрации") }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}
sealed class RegisterUiEffect {
    sealed class Navigate : RegisterUiEffect() {
        data object ToMain : Navigate()
        data object ToLogin : Navigate()

        //Todo(Подумать над реализацией экрана верификации)
        data object ToVerificationPending : Navigate()

    }
    data class ShowMessageAndNavigate(
        val message: String,
        val navigateTo: Navigate
    ) : RegisterUiEffect()


}