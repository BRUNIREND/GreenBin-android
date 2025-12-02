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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                is RegisterUiEffect.Navigate.ToMain -> navController.navigate("main") {
                    popUpTo("welcome") {inclusive = true}
                }
                is RegisterUiEffect.Navigate.ToLogin -> navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
                is RegisterUiEffect.Navigate.ToVerificationPending -> TODO()
                is RegisterUiEffect.ShowMessageAndNavigate -> {
                    snackbarHostState.showSnackbar(effect.message)
                    effect.navigateTo.let {
                        when (it) {
                            RegisterUiEffect.Navigate.ToMain -> navController.navigate("main") {
                                popUpTo(
                                    0
                                )
                            }

                            RegisterUiEffect.Navigate.ToVerificationPending -> navController.navigate(
                                "verification_pending"
                            )

                            RegisterUiEffect.Navigate.ToLogin -> navController.navigate("login")
                        }
                    }
                }

            }
        }
    }



    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CustomTopAppBar(
                titleRes =R.string.registration_appbar_text,
                onBackClick = {navController.popBackStack()},
            )
        }
    )
    {  innerPadding ->
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
        ){
            RegistrationContent(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@Composable
fun RegistrationContent(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState,
    onEvent: (RegisterUiEvent) -> Unit
){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var checked by remember {mutableStateOf(true)}
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        CustomInput(
            modifier = Modifier
                .width(327.dp)
                .height(52.dp),
            onValueChange = {  onEvent(RegisterUiEvent.NameChanged(it)) },
            value = uiState.name,
            type = InputFieldType.USERNAME,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        CustomInput(
            modifier = Modifier
                .width(327.dp)
                .height(52.dp),
            onValueChange = {  onEvent(RegisterUiEvent.EmailChanged(it)) },
            value = uiState.email,
            type = InputFieldType.EMAIL,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        CustomInput(
            modifier = Modifier
                .width(327.dp)
                .height(52.dp),
            onValueChange = {  onEvent(RegisterUiEvent.PasswordChanged(it)) },
            value = uiState.password,
            type = InputFieldType.PASSWORD,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        CustomInput(
            modifier = Modifier
                .width(327.dp)
                .height(52.dp),
            onValueChange = {  onEvent(RegisterUiEvent.ConfirmPasswordChanged(it)) },
            value = uiState.confirmPassword,
            type = InputFieldType.PASSWORD,
        )
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Row (
            modifier = Modifier.height(32.dp).fillMaxWidth().padding(start = 58.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Checkbox(
                checked = uiState.agreeToTermsChanged,
                modifier = Modifier.size(18.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(R.color.primary5), // зелёный при активном
                    uncheckedColor = Color.Gray,      // серый при неактивном
                    checkmarkColor = Color.White       // цвет галочки
                ),
                onCheckedChange = {
                    onEvent(RegisterUiEvent.AgreeToTermsChanged(it))
                }
            )
            Text(
                modifier = modifier.padding(start = 4.dp).fillMaxWidth(),
                text = stringResource(R.string.agree_license_agreement),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        CustomButton(
            onClick = {onEvent(RegisterUiEvent.RegisterClicked)},
            text = "Зарегистрироваться",
            modifier = Modifier
                .width(327.dp)
                .height(52.dp),
            filled = true,
            enabled = !uiState.isLoading
        )
//            if (uiState.isLoading) {
//                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
//            } else {
//                Text("Зарегистрироваться")
//            }
        uiState.error?.let {
            Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        TextButton(
            onClick = {
                onEvent(RegisterUiEvent.LoginClicked)
            },
            ) {
            Text(
                text = "Войти",
                color = colorResource(R.color.primary5),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

//@Composable
//@Preview(
//    showSystemUi = true,
//    showBackground = true,)
//fun PreviewRegistrationContent(){
//    RegistrationScreen()
//}

//@Composable
//@Preview(device = "id:pixel_2", showSystemUi = true, showBackground = true)()
//fun PreviewRegistrationContent(){
//    Scaffold (
//        topBar = {
//            CustomTopAppBar(
//                titleRes =R.string.registration_appbar_text,
//            )
//        }
//    ){  innerPadding ->
//        Surface (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
//        ){
//            RegistrationContent(
//                modifier = Modifier.fillMaxSize(),
//                uiState = RegisterUiState(),
//                onEvent = {}
//            )
//        }
//    }
//
//}