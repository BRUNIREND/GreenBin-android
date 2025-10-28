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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenbin.R
import com.example.greenbin.presentation.ui.components.CustomButton
import com.example.greenbin.presentation.ui.components.OtpCodeField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckMailScreen(
    onBackClick: () -> Unit,
    onEnterClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = "Проверьте вашу почту",
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
        ) {
            CheckMailContent()
        }

    }

}

@Composable
fun CheckMailContent(){
    Column (

        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.width(327.dp),
            text = stringResource(R.string.check_mail_test),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(28.dp))
        OtpCodeField { code ->
            println("Введённый код: $code")
        }
        Spacer(Modifier.height(16.dp))
        CustomButton(
            modifier = Modifier.width(327.dp).height(52.dp),
            text = "Продолжить",
            onClick = {},
            filled = true,
            enabled = false
        )
    }
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true)
fun ShowPreviewCheckMail(){
    CheckMailScreen({}, {})
}