package com.example.greenbin.presentation.ui.screens.auth

import android.R.attr.text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenbin.presentation.ui.components.CustomInput
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.example.greenbin.R
import com.example.greenbin.presentation.ui.components.MainButton
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    onBackClick: () -> Unit,
    onEnterClick: () -> Unit
){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text="Вход",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            // Стрелки задаются теперь так
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 28.dp)
        ) {
            ContentAuthentification()
        }

    }

}

@Composable
fun ContentAuthentification(){
    var userInput by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomInput(
            modifier = Modifier.width(327.dp).height(52.dp),
            textLabel = "E-mail",
            textPlaceholder = "example@mail.com",
            keyboardType = KeyboardType.Email,
            onValueChange = { userInput = it },
            value = ""
        )
        CustomInput(
            modifier = Modifier.width(327.dp).height(52.dp),
            textLabel = "Пароль",
            textPlaceholder = "Введите пароль",
            keyboardType = KeyboardType.Password,
            onValueChange = {userInput = it},
            value = "",
            isPasswordField = true
        )
        Spacer(modifier = Modifier.height(44.dp))
        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.End).padding(end = 16.dp)

        ) {
            Text(
                text = "Забыли пароль?",
                color = colorResource(R.color.primary4),
                style = MaterialTheme.typography.bodyMedium,

            )
        }
        MainButton(
            text = "Войти",
            modifier = Modifier.width(327.dp).height(44.dp),
            filled = true,
            enabled = false,
            onClick = {}
        )
        TextButton(
            onClick = {},

        ) {
            Text(
                text = "Зарегистрироваться",
                color = colorResource(R.color.primary5),
                style = MaterialTheme.typography.titleSmall,

            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ShowPreviewRegistration(){
    RegistrationScreen({}, {})
}