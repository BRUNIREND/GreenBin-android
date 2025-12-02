package com.example.greenbin.features.feature_auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.greenbin.R
import com.example.greenbin.presentation.ui.components.CustomButton
import com.example.greenbin.presentation.ui.components.CustomInput
import com.example.greenbin.presentation.ui.components.CustomTopAppBar
import com.example.greenbin.presentation.ui.components.InputFieldType
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthentificationScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest {effect ->
             when (effect) {
                is AuthUiEffect.Navigate.ToMain -> navController.navigate("main") {
                    popUpTo("welcome") {inclusive = true}
                }
                 is AuthUiEffect.Navigate.ToRegister -> navController.navigate("register")
                 {
                     popUpTo("login")
                     { inclusive = true }
                 }
                 is AuthUiEffect.Navigate.ToForgotPassword -> navController.navigate("forgot_password")
             }
        }
    }

    Scaffold (
        topBar = {
            CustomTopAppBar(
                titleRes = R.string.enter,
                onBackClick = {navController.popBackStack()},
            )
        }
    ){ innerPadding ->
        LoginContent(
            Modifier.padding(innerPadding),
            uiState = uiState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onEvent: (AuthUiEvent) -> Unit,
    uiState: AuthUiState,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomInput(
            modifier = Modifier
                .width(327.dp)
                .height(52.dp),
            onValueChange = {  onEvent(AuthUiEvent.EmailChanged(it)) },
            value = uiState.email,
            type = InputFieldType.EMAIL,
        )
        CustomInput(
            modifier = Modifier
                .width(327.dp)
                .height(52.dp),
            onValueChange = { onEvent(AuthUiEvent.PasswordChanged(it)) },
            value = uiState.password,
            type = InputFieldType.PASSWORD,
        )
        Spacer(modifier = Modifier.height(44.dp))
        TextButton(
            onClick = { onEvent(AuthUiEvent.ForgotPasswordClicked)},
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)

        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                color = colorResource(R.color.primary4),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        CustomButton(
            text = stringResource(R.string.loginscreen_mb_enter),
            modifier = Modifier
                .width(327.dp)
                .height(44.dp),
            filled = true,
            enabled = !uiState.isLoading,
            onClick = {onEvent(AuthUiEvent.LoginClicked)}
        )
        TextButton(
            onClick = {onEvent(AuthUiEvent.RegisterClicked)},
            ) {
            Text(
                text = stringResource(R.string.registrate_text),
                color = colorResource(R.color.primary5),
                style = MaterialTheme.typography.titleSmall,
            )
        }
        uiState.error?.let {
            Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
    }
}


//@Composable
//@Preview(showBackground = true)
//fun ShowPreviewRegistration() {
//    RegistrationScreen({}, {})
//}