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

        // Клиентская валидация
        if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(error = "Пароли не совпадают") }
            return
        }
        if (state.password.length < 8) {
            _uiState.update { it.copy(error = "Пароль слишком короткий (минимум 8 символов)") }
            return
        }
        if (state.password == state.password.lowercase()) {
            _uiState.update { it.copy(error = "Пароль должен содержать хотя бы одну заглавную букву") }
            return
        }
        if (!state.agreeToTermsChanged) {
            _uiState.update { it.copy(error = "Необходимо принять условия") }
            return
        }

        // Запускаем корутину внутри ViewModel
        // viewModelScope — это корутина, привязанная к жизненному циклу ViewModel
        // Она автоматически отменяется, когда ViewModel уничтожается (например, при выходе из экрана)
        // Это предотвращает утечки памяти
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            // Здесь мы переключаемся на фоновый поток (IO)
            // Firebase Auth (registerUseCase) выполняет сетевые операции и блокирующие вызовы
            // Их НЕЛЬЗЯ делать на главном потоке (Main) — будет краш приложения
            val result = withContext(Dispatchers.IO) {
                registerUseCase(state.email, state.password)
            }

            result.onSuccess { user ->
                try {
                    // Отправка письма подтверждения — тоже сетевая операция
                    // Поэтому тоже должна быть в IO-потоке
                    withContext(Dispatchers.IO) {
                        FirebaseAuth.getInstance().currentUser!!.sendEmailVerification().await()
                    }

                    // Успешно — отправляем эффект с сообщением и навигацией
                    _uiEffect.send(
                        RegisterUiEffect.ShowMessageAndNavigate(
                            message = "Письмо для подтверждения отправлено на ${state.email}",
                            navigateTo = RegisterUiEffect.Navigate.ToMain
                        )
                    )
                } catch (e: Exception) {
                    // Письмо не ушло, но регистрация прошла — всё равно пускаем дальше
                    _uiEffect.send(
                        RegisterUiEffect.ShowMessageAndNavigate(
                            message = "Аккаунт создан, но письмо подтверждения не отправлено",
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