package com.example.greenbin.features.feature_welcome.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenbin.R
import com.example.greenbin.presentation.ui.components.CustomButton
import kotlinx.coroutines.flow.collectLatest
import androidx.hilt.navigation.compose.hiltViewModel



@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToMap: () -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event){
                WelcomeUiEvent.NavigateToLogin -> onNavigateToLogin()
                WelcomeUiEvent.NavigateToMap -> onNavigateToMap()
                WelcomeUiEvent.NavigateToRegister -> onNavigateToRegister()
            }

        }
    }

    val image = painterResource(R.drawable.trashholderbig)
    Scaffold (
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .width(89.dp)
                    .height(97.dp)
            )

            Text(
                text = "GreenBin",
                fontSize = 64.sp,
                letterSpacing = -5.sp,
                fontFamily = FontFamily(Font(R.font.lato_medium))

            )
            Text(
                text = "get best",
                fontSize = 36.sp,
                letterSpacing = 4.sp,
                fontFamily = FontFamily(Font(R.font.lato_medium))
            )
            Spacer(modifier = Modifier.height(104.dp))
            CustomButton(
                text = "Создать аккаунт",
                modifier = Modifier
                    .width(242.dp)
                    .height(44.dp),
                filled = true,
                enabled = true,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                text = "Вход",
                modifier = Modifier
                    .width(242.dp)
                    .height(44.dp),
                filled = true,
                enabled = true,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                text = "Посмотреть карту",
                modifier = Modifier
                    .width(242.dp)
                    .height(44.dp),
                filled = false,
                enabled = true,
                onClick = {},
            )

        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun ShowOnBoardPreview(){
//}
