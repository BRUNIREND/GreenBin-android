package com.example.greenbin.features.feature_register.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.greenbin.R
import com.example.greenbin.navigation.AppScreen
import com.example.greenbin.presentation.ui.components.CustomButton
import com.example.greenbin.presentation.ui.components.CustomInput
import com.example.greenbin.presentation.ui.components.CustomTopAppBar
import com.example.greenbin.presentation.ui.components.InputFieldType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is RegisterUiEffect.Navigate.ToMain -> {
                    navController.navigate(AppScreen.Main) {
                        popUpTo<AppScreen.Welcome> { inclusive = true }
                        launchSingleTop = true
                    }
                }
                is RegisterUiEffect.Navigate.ToLogin -> {
                    navController.navigate(AppScreen.Login)
                }
                is RegisterUiEffect.Navigate.ToVerificationPending -> {
                    // navController.navigate("verification_pending")
                    TODO("Экран ожидания верификации")
                }
                is RegisterUiEffect.ShowMessageAndNavigate -> {
                    snackbarHostState.showSnackbar(effect.message)
                    when (effect.navigateTo) {
                        is RegisterUiEffect.Navigate.ToMain -> {
                            navController.navigate(AppScreen.Main) {
                                popUpTo<AppScreen.Welcome> { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                        is RegisterUiEffect.Navigate.ToLogin -> {
                            navController.navigate(AppScreen.Login)
                        }
                        is RegisterUiEffect.Navigate.ToVerificationPending -> {
                            // navController.navigate("verification_pending")
                            TODO()
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CustomTopAppBar(
                titleRes = R.string.registration_appbar_text,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        RegistrationContent(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 28.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun RegistrationContent(
    uiState: RegisterUiState,
    onEvent: (RegisterUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CustomInput(
            value = uiState.name,
            onValueChange = { onEvent(RegisterUiEvent.NameChanged(it)) },
            type = InputFieldType.USERNAME,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomInput(
            value = uiState.email,
            onValueChange = { onEvent(RegisterUiEvent.EmailChanged(it)) },
            type = InputFieldType.EMAIL,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomInput(
            value = uiState.password,
            onValueChange = { onEvent(RegisterUiEvent.PasswordChanged(it)) },
            type = InputFieldType.PASSWORD,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomInput(
            value = uiState.confirmPassword,
            onValueChange = { onEvent(RegisterUiEvent.ConfirmPasswordChanged(it)) },
            type = InputFieldType.PASSWORD,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = uiState.agreeToTermsChanged,
                onCheckedChange = { onEvent(RegisterUiEvent.AgreeToTermsChanged(it)) },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(R.color.primary5),
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = stringResource(R.string.agree_license_agreement),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            onClick = { onEvent(RegisterUiEvent.RegisterClicked) },
            text = "Зарегистрироваться",
            modifier = Modifier.fillMaxWidth(),
            filled = true,
            enabled = !uiState.isLoading && uiState.agreeToTermsChanged
        )

        if (uiState.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 2.dp,
            color = Color.White
            )
        }

        uiState.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { onEvent(RegisterUiEvent.LoginClicked) }
        ) {
            Text(
                text = "Войти",
                color = colorResource(R.color.primary5),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}