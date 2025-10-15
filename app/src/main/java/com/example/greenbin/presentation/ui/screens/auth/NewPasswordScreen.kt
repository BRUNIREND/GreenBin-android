package com.example.greenbin.presentation.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenbin.R
import com.example.greenbin.presentation.ui.components.CustomInput
import com.example.greenbin.presentation.ui.components.MainButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(
    onBackClick: () -> Unit,
    onEnterClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(start = 16.dp),
                title = {
                    Text(
                        text = stringResource(R.string.new_password_title),
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
    ) { innerPadding ->
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
        ){
            NewPasswordContent(
                modifier = Modifier
            )
        }

    }

}

@Composable
fun NewPasswordContent(modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.width(327.dp),
            text = stringResource(R.string.new_password_text),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(28.dp))
        CustomInput(
            modifier = Modifier.width(327.dp).height(52.dp),
            textLabel = "Пароль",
            textPlaceholder = "Пароль",
            isPasswordField = true,
            value = "asd", //Переписать
            onValueChange = {},
            keyboardType = KeyboardType.Password

        )
        Spacer(Modifier.height(16.dp))
        CustomInput(
            modifier = Modifier.width(327.dp).height(52.dp),
            textLabel = "Повторите пароль",
            textPlaceholder = "Повторите пароль",
            isPasswordField = true,
            value = "asd", //Переписать
            onValueChange = {},
            keyboardType = KeyboardType.Password
        )
        Spacer(Modifier.height(16.dp))
        MainButton(
            modifier = Modifier.width(327.dp).height(52.dp),
            text = "Сохранить",
            filled = true,
            enabled = false,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ShowPreviewNewPassword(){
    NewPasswordScreen(
        onBackClick = {},
        onEnterClick = {}
    )
}