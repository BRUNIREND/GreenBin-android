package com.example.greenbin.features.feature_welcome.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.greenbin.R
import com.example.greenbin.navigation.AppScreen
import com.example.greenbin.presentation.ui.components.CustomButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navController: NavController
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    // Одноразовые эффекты
    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect){
                is WelcomeUiEffect.Navigate.ToLogin -> navController.navigate(AppScreen.Login)
                is WelcomeUiEffect.Navigate.ToRegister -> navController.navigate(AppScreen.Register)
                is WelcomeUiEffect.Navigate.ToMap -> navController.navigate(AppScreen.Map.createRoute())
                is WelcomeUiEffect.Navigate.ToMain -> navController.navigate(AppScreen.Main) {
                    popUpTo<AppScreen.Welcome> {inclusive = true}
                    launchSingleTop = true
                }
            }

        }
    }
    WelcomeContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )


}

@Composable
private fun WelcomeContent(
    uiState: WelcomeUiState,
    onEvent: (WelcomeUiEvent) -> Unit,
){
    val image = painterResource(R.drawable.trashholderbig)
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
            enabled = !uiState.isLoading,
            onClick = { onEvent(WelcomeUiEvent.RegisterClicked)},
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            text = "Вход",
            modifier = Modifier
                .width(242.dp)
                .height(44.dp),
            filled = true,
            enabled = !uiState.isLoading,
            onClick = { onEvent(WelcomeUiEvent.LoginClicked)},
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            text = "Посмотреть карту",
            modifier = Modifier
                .width(242.dp)
                .height(44.dp),
            filled = false,
            enabled = !uiState.isLoading,
            onClick = { onEvent(WelcomeUiEvent.MapClicked)},
        )
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 24.dp))
        }

        uiState.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
@Composable
@Preview(showBackground = true, device = "id:pixel_2",
    wallpaper = Wallpapers.NONE
)
fun ShowOnBoardPreview(){
    val image = painterResource(R.drawable.trashholderbig)
    Column(
        modifier = Modifier.fillMaxSize(),
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
            onClick = { },
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
            enabled = false,
            onClick = {},
        )
    }
}
